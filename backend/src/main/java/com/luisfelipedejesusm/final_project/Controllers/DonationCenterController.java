package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.BloodBankDTO;
import com.luisfelipedejesusm.final_project.DTOs.Models.DonationCenterDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.BloodBankRequest;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationCenterRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.MessageResponse;
import com.luisfelipedejesusm.final_project.Models.BloodBank;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Services.BloodBankService;
import com.luisfelipedejesusm.final_project.Services.DonationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("${endpoint}/donation-center")
public class DonationCenterController {
    @Autowired
    private DonationCenterService donationCenterService;

//    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("")
    public Map<String, Object> createNewDonationCenter(@Valid @RequestBody DonationCenterRequest request){
        var id = donationCenterService.createNewDonationCenter(request);
        return new HashMap<String, Object>(Map.of(
                "msg", "Created Successfully",
                "id", id
        ));
    }

    @GetMapping("")
    public List<DonationCenterDTO> getAllDonationCenters(){
        List<DonationCenter> donationCenters = donationCenterService.getAllDonationCenters();
        return donationCenters.stream().map(DonationCenterDTO::new).collect(Collectors.toList());
    }

//    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public MessageResponse updateDonationCenter(@PathVariable Long id, @Valid @RequestBody DonationCenterRequest request){
        donationCenterService.updateDonationCenter(id, request);
        return new MessageResponse("Donation Center Updated Successfully");
    }

//    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @DeleteMapping("/{id}")
    public MessageResponse deleteDonationCenter(@PathVariable Long id){
        donationCenterService.deleteDonationCenter(id);
        return new MessageResponse("Donation Center Updated Successfully");
    }
}
