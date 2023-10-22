package com.example.estagio_pokemon_triagil.controllers;

import com.example.estagio_pokemon_triagil.models.dtos.InputTeamDTO;
import com.example.estagio_pokemon_triagil.models.dtos.OutputTeamDTO;
import com.example.estagio_pokemon_triagil.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllTeams() {
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputTeamDTO> getTeamById(@PathVariable Integer id){
        return ResponseEntity.ok(teamService.findTeamById(id));
    }

    @PostMapping
    public ResponseEntity<String> createAndReturnTeam(@RequestBody @Valid InputTeamDTO teamDTO){
        Integer team_id = teamService.saveTeam(teamDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(team_id).toUri();
        return ResponseEntity.created(location).body("Time Criado Com Sucesso!!!\nO ID Do Time e %d".formatted(team_id));
    }
}
