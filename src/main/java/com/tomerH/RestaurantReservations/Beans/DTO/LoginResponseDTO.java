package com.tomerH.RestaurantReservations.Beans.DTO;

import com.tomerH.RestaurantReservations.Beans.UserCredentials;

public class LoginResponseDTO {

    private String jwt;
    private UserCredentials user;


    public LoginResponseDTO(UserCredentials user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public LoginResponseDTO() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserCredentials getUser() {
        return user;
    }

    public void setUser(UserCredentials user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "jwt='" + jwt + '\'' +
                ", user=" + user +
                '}';
    }
}
