package com.example.estagio_pokemon_triagil.components;

import com.example.estagio_pokemon_triagil.models.entities.Pokemon;
import com.example.estagio_pokemon_triagil.models.entities.Team;
import com.example.estagio_pokemon_triagil.repositories.PokemonRepository;
import com.example.estagio_pokemon_triagil.repositories.TeamRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;

@Component
@Profile("TESTE")
//@DependsOn({ "dataSource" })
public class PopulateDatabaseInTestProfile {

    private final PokemonRepository pokemonRepository;
    private final TeamRepository teamRepository;

    public PopulateDatabaseInTestProfile(PokemonRepository pokemonRepository, TeamRepository teamRepository) {
        this.pokemonRepository = pokemonRepository;
        this.teamRepository = teamRepository;
    }

    @PostConstruct
    public void populateDataBase() {
        Pokemon poke_1 = new Pokemon(132, "ditto", 40.0, 3.0);
        Pokemon poke_2 = new Pokemon(25, "pikachu", 60.0, 4.0);
        Pokemon poke_3 = new Pokemon(131, "lapras", 2200.0, 25.0);
        Pokemon poke_4 = new Pokemon(501, "oshawott", 59.0, 5.0);
        Pokemon poke_5 = new Pokemon(393, "piplup", 52.0, 4.0);
        Pokemon poke_6 = new Pokemon(158, "totodile", 95.0, 6.0);
        Pokemon poke_7 = new Pokemon(6, "charizard", 905.0, 17.0);
        Pokemon poke_8 = new Pokemon(9, "blastoise", 855.0, 16.0);
        Pokemon poke_9 = new Pokemon(3, "venusaur", 1000.0, 20.0);



        Team team_1 = new Team("Red", new HashSet<>(List.of(poke_1,poke_2,poke_3)));
        Team team_2 = new Team("Thiago", new HashSet<>(List.of(poke_4,poke_5,poke_6)));
        Team team_3 = new Team("Red", new HashSet<>(List.of(poke_7,poke_8,poke_9)));

        pokemonRepository.saveAll(List.of(poke_1,poke_2,poke_3,poke_4,poke_5,poke_6,poke_7,poke_8,poke_9));
        teamRepository.saveAll(List.of(team_1,team_2,team_3));
    }
}
