package com.tomerH.RestaurantReservations.Controllers;

import com.tomerH.RestaurantReservations.Beans.DTO.LoginResponseDTO;
import com.tomerH.RestaurantReservations.Beans.DTO.RegistrationDTO;
import com.tomerH.RestaurantReservations.Beans.Member;
import com.tomerH.RestaurantReservations.Beans.UserCredentials;
import com.tomerH.RestaurantReservations.Exceptions.LoginFailedException;
import com.tomerH.RestaurantReservations.Services.AuthenticationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {


    private final AuthenticationService credentialsService;

    public AuthController(@Lazy AuthenticationService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping("/register/member")
    public LoginResponseDTO registerMember(@RequestBody RegistrationDTO body) throws Exception {
        return credentialsService.memberRegistration(
                body.getPassword(),
                body.getUsername(),
                body.getFirstName(),
                body.getLastName(),
                body.getPhoneNumber(),
                body.getEmail());

    }

    @PostMapping("/login/member")
    public LoginResponseDTO login(@RequestBody RegistrationDTO loginBody) throws LoginFailedException {
        return credentialsService.loginMember(loginBody.getUsername(), loginBody.getPassword());
    }
}
