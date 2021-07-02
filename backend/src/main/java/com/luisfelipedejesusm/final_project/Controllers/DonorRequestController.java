package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Responses.DonorRequestPayload;
import com.luisfelipedejesusm.final_project.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/request")
public class DonorRequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("donorRequest")
    public ResponseEntity<?> createNewDonorRequest(@Valid @RequestBody DonorRequestPayload donorRequestPayload){
        return requestService.createDonorRequest(donorRequestPayload);
    }

    @GetMapping("getDonorRequests")
    public ResponseEntity<?> getListOfDonorRequests(){
        return requestService.getListOfDonorRequests();
    }

    @GetMapping("getMyDonorRequests")
    public ResponseEntity<?> getListOfUserDonorRequests(){
        return requestService.getListOfUserDonorRequests();
    }

    @PutMapping("donorRequest/{requestId}")
    @PatchMapping("donorRequest/{requestId}")
    public ResponseEntity<?> updateDonorRequest(@PathVariable Long requestId, @Valid @RequestBody DonorRequestPayload donorRequestPayload){
        return requestService.updateDonorRequest(requestId, donorRequestPayload);
    }

    @DeleteMapping("donorRequest/{requestId}")
    public ResponseEntity<?> deleteDonorRequest(@PathVariable Long requestId){
        return requestService.deleteDonorRequest(requestId);
    }
}
