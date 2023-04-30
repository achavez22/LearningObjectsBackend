package com.exam.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User with this username is already there in DB!! try with another username");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }
}
