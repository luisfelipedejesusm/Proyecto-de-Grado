package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.DonorRequestPayload;
import com.luisfelipedejesusm.final_project.DTOs.MessageResponse;
import com.luisfelipedejesusm.final_project.DTOs.Responses.DonorRequestResponse;
import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Models.DonorRequest;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.DonorRequestRepository;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class RequestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonorRequestRepository donorRequestRepository;

    public ResponseEntity<?> createDonorRequest(DonorRequestPayload requestPayload){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        DonorRequest donorRequest = new DonorRequest();
        donorRequest.setBloodType(requestPayload.getBloodType());
        donorRequest.setUser(userDetails.getUser());
        donorRequest.setDonor(userRepository.getById(requestPayload.getDonorId()));
        donorRequest.setComments(requestPayload.getComments());

        var id = donorRequestRepository.save(donorRequest);
        return  ResponseEntity.ok(new MessageResponse("Donor request created successfully"));
    }

    public ResponseEntity<?> getListOfDonorRequests() {
        var listOfRequests =
                donorRequestRepository.findAll().stream().map(DonorRequestResponse::new);
        return ResponseEntity.ok(Map.of("data", listOfRequests));
    }

    public ResponseEntity<?> getListOfUserDonorRequests() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var listOfRequests =
                donorRequestRepository.findAllByUserId(userDetails.getId()).stream().map(DonorRequestResponse::new);
        return ResponseEntity.ok(Map.of("data", listOfRequests));
    }

    public ResponseEntity<?> updateDonorRequest(Long requestId, DonorRequestPayload data) {

        DonorRequest donorRequest = donorRequestRepository.findById(requestId).orElse(null);
        if(donorRequest != null){
            donorRequest.setDonor(new User(data.getDonorId()));
            donorRequest.setComments(data.getComments());
            donorRequest.setBloodType(data.getBloodType());
            donorRequestRepository.save(donorRequest);
        }
        return  ResponseEntity.ok(new MessageResponse("Donor Request updated successfully"));

    }

    public ResponseEntity<?> deleteDonorRequest(Long requestId) {
        if(donorRequestRepository.existsById(requestId))
            donorRequestRepository.deleteById(requestId);
        return  ResponseEntity.ok(new MessageResponse("Donor Request deleted successfully"));
    }
}
