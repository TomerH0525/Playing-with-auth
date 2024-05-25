package com.tomerH.RestaurantReservations.Exceptions;

public class WeakPasswordExeption extends Exception{
    public WeakPasswordExeption() {
        super("Password is weak...");
    }

    public WeakPasswordExeption(String message) {
        super(message);
    }
}
