package com.tomerH.RestaurantReservations.Exceptions;

public class RoleException extends Exception{
    public RoleException() {
        super("Unable to apply role error...");
    }

    public RoleException(String message) {
        super(message);
    }
}
