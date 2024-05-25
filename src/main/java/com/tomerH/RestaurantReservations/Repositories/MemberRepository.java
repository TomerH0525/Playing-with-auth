package com.tomerH.RestaurantReservations.Repositories;

import com.tomerH.RestaurantReservations.Beans.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
