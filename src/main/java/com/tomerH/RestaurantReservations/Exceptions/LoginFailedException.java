package com.tomerH.RestaurantReservations.Exceptions;

public class LoginFailedException extends Exception {
    public LoginFailedException() {
        super("Login Failed: Your user ID or password is incorrect...");
    }

    public LoginFailedException(String msg){
        super("Login Failed: "+msg);
    }
}
