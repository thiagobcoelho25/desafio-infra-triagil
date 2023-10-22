package com.example.estagio_pokemon_triagil.models.dtos;


import com.example.estagio_pokemon_triagil.models.entities.Pokemon;

import java.util.Set;

public record OutputTeamDTO(String owner, Set<Pokemon> pokemons) {
}
