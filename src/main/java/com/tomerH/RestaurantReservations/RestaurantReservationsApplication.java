package com.tomerH.RestaurantReservations;

import com.tomerH.RestaurantReservations.Beans.Role;
import com.tomerH.RestaurantReservations.Beans.UserCredentials;
import com.tomerH.RestaurantReservations.Repositories.RoleRepository;
import com.tomerH.RestaurantReservations.Repositories.UserCredentialsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RestaurantReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantReservationsApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserCredentialsRepository credentialsRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("MEMBER"));
			roleRepository.save(new Role("RESTAURANT"));
			roleRepository.save(new Role("VISITOR"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			UserCredentials admin = new UserCredentials(1,"admin",passwordEncoder.encode("Admin1234"),roles);

			credentialsRepository.save(admin);
		};
	}
}
