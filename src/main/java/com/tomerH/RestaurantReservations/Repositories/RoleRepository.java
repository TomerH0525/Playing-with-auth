package com.tomerH.RestaurantReservations.Repositories;

import com.tomerH.RestaurantReservations.Beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByAuthority(String authority);
}
