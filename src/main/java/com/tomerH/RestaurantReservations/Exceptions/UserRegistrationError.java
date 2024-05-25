package com.tomerH.RestaurantReservations.Exceptions;

public class UserRegistrationError extends Exception{
    public UserRegistrationError(){
        super("User Registration Error");
    }

    public UserRegistrationError(String message) {
        super(message);
    }
}
