package com.example.estagio_pokemon_triagil.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class InputTeamDTO {

    @NotBlank(message = "Nome é obrigatório.")
    @Length(min = 3, max = 64, message = "Nome deve conter entre 3 e 64 caracteres.")
    @Pattern(regexp="^[A-Za-z]*$",message = "Deve Ser Uma String")
    private String owner;

    @NotEmpty(message = "Lista de Pokemons Não Pode Ser Vazia")
    private List< @NotBlank(message = "Nome Pokemon Não Pode Ser Vazia") String> team;
}
