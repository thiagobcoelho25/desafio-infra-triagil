package com.example.estagio_pokemon_triagil.repositories;

import com.example.estagio_pokemon_triagil.models.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    List<Pokemon> findPokemonsByNameIn(Collection<String> name);
}
