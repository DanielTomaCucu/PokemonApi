package com.example.pokemoin.exceptions;



public class PokemonNotFoundEx extends RuntimeException{
    private static final long serialVersionUID=1;
    public PokemonNotFoundEx(String message){
        super(message);
    }
}
