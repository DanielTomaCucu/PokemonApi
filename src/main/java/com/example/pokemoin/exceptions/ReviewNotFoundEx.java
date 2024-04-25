package com.example.pokemoin.exceptions;

public class ReviewNotFoundEx extends RuntimeException{
    private static final long serialVersionUID=1;
    public  ReviewNotFoundEx(String message){
        super(message);
    }


}
