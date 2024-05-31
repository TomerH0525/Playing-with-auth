package com.tomerH.RestaurantReservations.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String onlyAdmin(){
        return "hi admin";
    }
}
