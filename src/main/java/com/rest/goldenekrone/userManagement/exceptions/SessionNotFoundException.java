package com.rest.goldenekrone.userManagement.exceptions;

public class SessionNotFoundException extends NotFounderClassException{
    public SessionNotFoundException(String message){
        super(message);
    }
}
