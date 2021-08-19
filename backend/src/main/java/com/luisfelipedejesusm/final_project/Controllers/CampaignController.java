package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.CampaignDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.MessageResponse;
import com.luisfelipedejesusm.final_project.Models.Campaign;
import com.luisfelipedejesusm.final_project.Services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${endpoint}/campaign")
public class CampaignController {

    @Autowired
    private CampaignService service;

    @PostMapping("")
    public MessageResponse createNewCampaign(@Valid @RequestBody CampaignDTO request){
        service.newCampaign(request);
        return new MessageResponse("Donation Appointment Created Successfully");
    }

    @PostMapping("/activation/{id}")
    public MessageResponse activationToggle(@PathVariable Long id){
        service.activationToggle(id);
        return new MessageResponse("Donation Appointment Created Successfully");
    }

    @GetMapping("")
    public List<CampaignDTO> getCampaigns(){
        return service.getCampaigns().stream().map(CampaignDTO::new).collect(Collectors.toList());
    }
}
