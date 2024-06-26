package com.example.pokemoin.service.impl;

import com.example.pokemoin.dto.PokemonDto;
import com.example.pokemoin.dto.ReviewDto;
import com.example.pokemoin.exceptions.PokemonNotFoundEx;
import com.example.pokemoin.exceptions.ReviewNotFoundEx;
import com.example.pokemoin.models.Pokemon;
import com.example.pokemoin.models.Review;
import com.example.pokemoin.repository.PokemonRepo;
import com.example.pokemoin.repository.ReviewRepo;
import com.example.pokemoin.service.PokemonService;
import com.example.pokemoin.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepo reviewRepo;
    private PokemonRepo pokemonRepo;
    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo, PokemonRepo pokemonRepo){
        this.pokemonRepo= pokemonRepo;
        this.reviewRepo= reviewRepo;

    }
    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review= mapToEntity(reviewDto);
        Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundEx("Pokemon with review not found"));
        review.setPokemon(pokemon);
        Review newREview = reviewRepo.save(review);
        return mapToDto(newREview);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id) {
        List<Review> reviews= reviewRepo.findByPokemonId(id);
        return  reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
       Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundEx("Pokemon with review not found"));

       Review review = reviewRepo.findById(reviewId).orElseThrow(()-> new ReviewNotFoundEx("REview not found"));

       if(review.getPokemon().getId() != pokemon.getId() ){
           throw new ReviewNotFoundEx("This review do not belong to this pokemon");
       }
       return mapToDto(review);

    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundEx("Pokemon not found"));
        Review review= reviewRepo.findById(reviewId).orElseThrow(()-> new ReviewNotFoundEx("Review not found"));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw  new ReviewNotFoundEx("Review not found");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        Review updateReview = reviewRepo.save(review);
        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {
        Pokemon pokemon= pokemonRepo.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundEx("Pokemon not found"));
        Review review = reviewRepo.findById(reviewId).orElseThrow(()-> new ReviewNotFoundEx("Review not found"));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw  new ReviewNotFoundEx("Review not found");
        }
        reviewRepo.delete(review);
    }


    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto= new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
    private Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
