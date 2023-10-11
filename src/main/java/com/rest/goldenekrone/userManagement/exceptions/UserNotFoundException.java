package com.rest.goldenekrone.userManagement.exceptions;

public class UserNotFoundException extends  NotFounderClassException{

    public UserNotFoundException(String message){
        super(message);
    }
}
