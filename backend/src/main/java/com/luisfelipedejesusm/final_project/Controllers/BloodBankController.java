package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.BloodBankDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.BloodBankRequest;
import com.luisfelipedejesusm.final_project.Models.BloodBank;
import com.luisfelipedejesusm.final_project.Services.BloodBankService;
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
@RequestMapping("${endpoint}/bloodbank")
public class BloodBankController {

    @Autowired
    private BloodBankService bloodBankService;

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("")
    public Map<String, Object> createNewBloodBank(@Valid @RequestBody BloodBankRequest request){
        var id = bloodBankService.createNewBloodBank(request);
        return new HashMap<String, Object>(Map.of(
                "msg", "Created Successfully",
                "id", id
        ));
    }

    @GetMapping("")
    public List<BloodBankDTO> getAllBloodBanks(){
        List<BloodBank> bloodBanks = bloodBankService.getAllBloodBanks();
        return bloodBanks.stream().map(BloodBankDTO::new).collect(Collectors.toList());
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBloodBank(@PathVariable Long id, @Valid @RequestBody BloodBankRequest request){
        bloodBankService.updateBloodBank(id, request);
        return ResponseEntity.ok("Blood Bank Updated Successfully");
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBloodBank(@PathVariable Long id){
        bloodBankService.deleteBloodBank(id);
        return ResponseEntity.ok("Blood Bank Updated Successfully");
    }
}
