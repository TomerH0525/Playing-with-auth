package com.tomerH.RestaurantReservations.Beans;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String restaurantName;
    private String cityLocation;
    private String address;
    private String email;
    private String restaurantPhone;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private ArrayList<RestaurantImages > images;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurants")
    private ArrayList<RestaurantTable> tables;
    @OneToOne
    private UserCredentials credentials;


    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<RestaurantImages> getImages() {
        return images;
    }

    public void setImages(ArrayList<RestaurantImages> images) {
        this.images = images;
    }

    public ArrayList<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(ArrayList<RestaurantTable> tables) {
        this.tables = tables;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    private UserCredentials getCredentials() {
        return credentials;
    }

    private void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }
}
