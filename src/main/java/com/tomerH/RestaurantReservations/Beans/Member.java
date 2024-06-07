package com.tomerH.RestaurantReservations.Beans;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @NonNull
    @Column(unique = true)
    private String email;
    @OneToOne
    @NonNull
    private UserCredentials credentials;
    @ManyToMany
    private Set<Restaurant> restaurants;

    public Member(){

    }

    public Member(@NonNull String firstName, String lastName, String phoneNumber, @NonNull String email, @NonNull UserCredentials credentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.credentials = credentials;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", credentials=" + credentials +
                ", restaurants=" + restaurants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
