package com.luisfelipedejesusm.final_project.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class IndexController {

    @GetMapping("index")
    public ResponseEntity<?> Index(){
        return ResponseEntity.ok(Map.of("data", "Welcome to my Grade project :)"));
    }
}
