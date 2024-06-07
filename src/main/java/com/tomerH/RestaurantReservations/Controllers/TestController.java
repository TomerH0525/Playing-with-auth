package com.tomerH.RestaurantReservations.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/member")
    public ResponseEntity<String> memberAccessOnly(){
        return ResponseEntity.ok("Hello member!");
    }
}
