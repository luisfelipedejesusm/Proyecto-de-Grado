package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.LoginRequest;
import com.luisfelipedejesusm.final_project.DTOs.RegisterRequest;
import com.luisfelipedejesusm.final_project.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<?> AuthenticateUser(@Valid @RequestBody LoginRequest loginData){
        return authenticationService.authenticateUser(loginData);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> RegisterUser(@Valid @RequestBody RegisterRequest registerData){
        return authenticationService.registerUser(registerData);
    }
}
