package com.example.pokemoin.controller;

import com.example.pokemoin.dto.PokemonDto;
import com.example.pokemoin.dto.PokemonResponse;
import com.example.pokemoin.models.Pokemon;
import com.example.pokemoin.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {
    private  PokemonService pokemonService;
  @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping("/pokemon")
    public ResponseEntity<PokemonResponse> getPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize){
        return new ResponseEntity<>(pokemonService.getAllPokemons(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable int id){
        return  ResponseEntity.ok(pokemonService.getPokemonById(id));
    }
    @PostMapping("/pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
  return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }
    @PutMapping("/pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable int id){
        PokemonDto response = pokemonService.updatePokemonById( pokemonDto,id);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @DeleteMapping("/pokemon/delete/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable int id){
        pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
