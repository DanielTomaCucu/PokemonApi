package com.example.pokemoin.service.impl;

import com.example.pokemoin.dto.PokemonDto;
import com.example.pokemoin.dto.PokemonResponse;
import com.example.pokemoin.exceptions.PokemonNotFoundEx;
import com.example.pokemoin.models.Pokemon;
import com.example.pokemoin.repository.PokemonRepo;
import com.example.pokemoin.service.PokemonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepo pokemonRepo;

    @Autowired
    public PokemonServiceImpl(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }


    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepo.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse;
    }

    @Override
    public PokemonResponse getAllPokemons(int pageNo, int pageSize) {
        Pageable  pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemons = pokemonRepo.findAll(pageable);
        List<Pokemon> listOfPokemons= pokemons.getContent();
        List<PokemonDto>content = listOfPokemons.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PokemonResponse pokemonResponse= new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNumber(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());
        return pokemonResponse;
    };

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepo.findById(id).orElseThrow(() -> new PokemonNotFoundEx("Pokemon could not be found"));
        return  mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemonById(PokemonDto pokemonDto, int id) {
        Pokemon pokemon= pokemonRepo.findById(id).orElseThrow(() -> new PokemonNotFoundEx("Pokemon Could not be updated"));
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        Pokemon updatedPokemon = pokemonRepo.save(pokemon);

        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = pokemonRepo.findById(id).orElseThrow(() -> new PokemonNotFoundEx("Pokemon Not found"));
        pokemonRepo.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon){
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }
    private Pokemon mapTOEntity(PokemonDto pokemonDto){
        Pokemon pokemon= new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
