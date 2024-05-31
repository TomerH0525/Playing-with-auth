package com.tomerH.RestaurantReservations.Controllers;

import com.tomerH.RestaurantReservations.Beans.DTO.LoginResponseDTO;
import com.tomerH.RestaurantReservations.Beans.DTO.RegistrationDTO;
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
    public UserCredentials registerMember(@RequestBody RegistrationDTO registrationBody) throws Exception {
        return credentialsService.registerMember(registrationBody.getUsername(),registrationBody.getPassword());
    }

    @PostMapping("/login/member")
    public LoginResponseDTO login(@RequestBody RegistrationDTO loginBody) throws LoginFailedException {
        return credentialsService.loginMember(loginBody.getUsername(), loginBody.getPassword());
    }
}
