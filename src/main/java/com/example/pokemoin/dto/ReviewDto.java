package com.example.pokemoin.dto;

import com.example.pokemoin.models.Pokemon;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDto {
    private int id;
    private String title;
    private  String content;
    private int stars;

}
