package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Requests.LoginRequest;
import com.luisfelipedejesusm.final_project.DTOs.Requests.RegisterRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.MessageResponse;
import com.luisfelipedejesusm.final_project.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
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
    public MessageResponse RegisterUser(@Valid @RequestBody RegisterRequest registerData) throws Exception {
        var res = authenticationService.registerUser(registerData);
        if(res != null){
            return new MessageResponse("User Registered Successfully");
        }
        return new MessageResponse("Username/Email already exists");
    }
}
