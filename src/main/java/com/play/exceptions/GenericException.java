package com.play.exceptions;

public class GenericException extends RuntimeException{
    public GenericException(String message){
        super(message);
    }

    public GenericException(){
        super("A generic exception");
    }
}
