package com.luisfelipedejesusm.final_project;

import com.luisfelipedejesusm.final_project.DTOs.Responses.DonorRequestPayload;
import com.luisfelipedejesusm.final_project.DTOs.Requests.LoginRequest;
import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import com.luisfelipedejesusm.final_project.Services.AuthenticationService;
import com.luisfelipedejesusm.final_project.Services.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class FinalProjectApplicationTests {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private UserRepository userRepository;

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

	@Test
	void getListOfDonors(){
		var res = userRepository.findByIsDonor(true);
	}

	@Test
	void createDonorRequest(){
		requestService.createDonorRequest(
				new DonorRequestPayload(
						EBloodType.O_NEGATIVE,
						1L,
						"Some Comment from tests"
				)
		);
	}

}
