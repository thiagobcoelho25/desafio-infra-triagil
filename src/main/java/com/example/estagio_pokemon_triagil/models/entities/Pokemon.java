package com.example.estagio_pokemon_triagil.models.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    @Id
    private Integer id;
    private String name;
    private Double weight;
    private Double height;


    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
