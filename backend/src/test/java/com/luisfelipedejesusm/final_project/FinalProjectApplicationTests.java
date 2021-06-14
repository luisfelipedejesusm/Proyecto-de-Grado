package com.luisfelipedejesusm.final_project;

import com.luisfelipedejesusm.final_project.DTOs.LoginRequest;
import com.luisfelipedejesusm.final_project.Services.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinalProjectApplicationTests {

	@Autowired
	private AuthenticationService authenticationService;

	@Test
	void contextLoads() {
	}

	@Test
	void unregisterUserSignIn(){
		authenticationService.authenticateUser(new LoginRequest(
				"luis",
				"1234"
		));
	}

}
