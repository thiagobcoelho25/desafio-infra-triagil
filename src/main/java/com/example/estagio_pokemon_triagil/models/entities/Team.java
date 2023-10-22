package com.example.estagio_pokemon_triagil.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String owner;
    @ManyToMany
    @JoinTable(name = "pokemons_team",
            joinColumns = { @JoinColumn(name = "team_id_fk") },
            inverseJoinColumns = { @JoinColumn(name = "pokemon_id_fk") })
    private Set<Pokemon> pokemons = new HashSet<>();

    public Team(String owner, Set<Pokemon> pokemons) {
        this.owner = owner;
        this.pokemons = pokemons;
    }
}
