package com.revature.exception;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String message){
        super(message);
    }
}
