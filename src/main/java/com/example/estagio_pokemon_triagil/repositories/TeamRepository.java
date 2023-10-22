package com.example.estagio_pokemon_triagil.repositories;

import com.example.estagio_pokemon_triagil.models.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
