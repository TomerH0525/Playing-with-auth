package com.tomerH.RestaurantReservations.Beans;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "restaurant_images")
public class RestaurantImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;
    private String imagePath;
    @ManyToOne
    @JoinColumn(name = "restaurant_owner_id")
    private Restaurant restaurant;

    public RestaurantImages() {
    }

    public int getImageId() {
        return imageId;
    }

    private void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    private void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    private void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "RestaurantImages{" +
                "imageId=" + imageId +
                ", imagePath='" + imagePath + '\'' +
                ", restaurantOwnerId=" + restaurant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantImages that = (RestaurantImages) o;
        return imageId == that.imageId && Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, restaurant);
    }
}
