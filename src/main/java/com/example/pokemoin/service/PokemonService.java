package com.example.pokemoin.service;

import com.example.pokemoin.dto.PokemonDto;
import com.example.pokemoin.dto.PokemonResponse;



public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemons(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemonById(PokemonDto pokemonDto, int id);
    void deletePokemon(int id);
}
