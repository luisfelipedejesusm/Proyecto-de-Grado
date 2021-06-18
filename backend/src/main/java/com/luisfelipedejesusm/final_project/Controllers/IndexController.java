package com.luisfelipedejesusm.final_project.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/test")
public class IndexController {

    @PostMapping("index")
    public String Index(){
        return "Welcome to my Grade project :)";
    }
}
