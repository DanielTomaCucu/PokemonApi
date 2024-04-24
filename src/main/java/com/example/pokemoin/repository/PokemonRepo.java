package com.example.pokemoin.repository;

import com.example.pokemoin.models.Pokemon;
import com.example.pokemoin.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {

}
