package com.tomerH.RestaurantReservations.Controllers;

import com.tomerH.RestaurantReservations.Beans.Member;
import com.tomerH.RestaurantReservations.Exceptions.UserRegistrationError;
import com.tomerH.RestaurantReservations.Services.UserCredentialsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserCredentialsService credentialsService;

    public AuthController(UserCredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping("/register/member")
    public long registerMember(@RequestBody Member member) throws Exception {
        return credentialsService.registerUser(member.getCredentials());
    }
}
