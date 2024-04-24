package com.example.pokemoin.repository;

import com.example.pokemoin.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(int pokemonId);
}
