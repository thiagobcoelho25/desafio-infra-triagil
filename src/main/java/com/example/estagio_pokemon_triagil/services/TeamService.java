package com.example.estagio_pokemon_triagil.services;

import com.example.estagio_pokemon_triagil.exceptions.BusinessRuleException;
import com.example.estagio_pokemon_triagil.models.dtos.InputTeamDTO;
import com.example.estagio_pokemon_triagil.models.dtos.OutputTeamDTO;
import com.example.estagio_pokemon_triagil.models.entities.Pokemon;
import com.example.estagio_pokemon_triagil.models.entities.Team;
import com.example.estagio_pokemon_triagil.repositories.PokemonRepository;
import com.example.estagio_pokemon_triagil.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PokemonRepository pokemonRepository;
    private final WebClient.Builder webClientBuilder;

    public TeamService(TeamRepository teamRepository, PokemonRepository pokemonRepository, WebClient.Builder webClientBuilder) {
        this.teamRepository = teamRepository;
        this.pokemonRepository = pokemonRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public OutputTeamDTO findTeamById(Integer id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Time Não Existente"));
        return new OutputTeamDTO(team.getOwner(), team.getPokemons());
    }

    public Integer saveTeam(InputTeamDTO inputTeamDTO) {
        Team team = new Team();
        team.setOwner(inputTeamDTO.getOwner());

        List<Pokemon> pokemons_from_db = pokemonRepository.findPokemonsByNameIn(inputTeamDTO.getTeam());

        /*VERIFICA SE HA POKEMONS NÃO CADASTRADOS NO DB LOCAL -> SE NÃO EXISTIR ALGUM PROCURA NA 'PókeAPI'*/
        if(inputTeamDTO.getTeam().size() > pokemons_from_db.size()){
            List<String> nome_novos_pokemons = inputTeamDTO.getTeam().stream().filter(pokemon_1 ->
                    pokemons_from_db.stream().noneMatch(pokemon_2 -> pokemon_2.getName().equalsIgnoreCase(pokemon_1))).toList();

            try {
                List<Pokemon> pokemons_from_api = getPokemonsFromPokeAPI(nome_novos_pokemons);
                List<Pokemon> saved_pokemons_from_api_to_db = pokemonRepository.saveAll(pokemons_from_api);


                team.setPokemons(new HashSet<>(Stream.concat(saved_pokemons_from_api_to_db.stream(), pokemons_from_db.stream()).toList()));

            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            team.setPokemons(new HashSet<>(pokemons_from_db));
        }

        Team saved_team = teamRepository.save(team);
        return saved_team.getId();
    }

    public Object findAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().collect(Collectors.toMap(Team::getId, team -> new OutputTeamDTO(team.getOwner(), team.getPokemons())));
    }

    private List<Pokemon> getPokemonsFromPokeAPI(List<String> pokemons) throws ExecutionException, InterruptedException {
        WebClient webClient = webClientBuilder.baseUrl("https://pokeapi.co/api/v2/pokemon").exchangeStrategies(ExchangeStrategies.builder().codecs(
                        clientCodecConfigurer ->
                                clientCodecConfigurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build()).build();

        List<CompletableFuture<Pokemon>> futures = pokemons.stream()
                .map(endpoint -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return webClient.get()
                                .uri("/{name}", endpoint)
                                .retrieve()
                                .bodyToMono(Pokemon.class)
                                .block();
                    } catch (Exception e) {
                        // Tratar erros individualmente para cada requisição
                        // Pode retornar uma resposta de fallback, registrar o erro, etc.
                        throw new BusinessRuleException("Pokemon %s Não Existe".formatted(endpoint));
                    }
                }))
                .toList();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        allOf.get();

        return futures.stream()
                .map(CompletableFuture::join)
                .toList();
    }
}
