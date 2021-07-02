package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.BloodBankDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.BloodBankRequest;
import com.luisfelipedejesusm.final_project.Models.BloodBank;
import com.luisfelipedejesusm.final_project.Repositories.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BloodBankService {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    public void createNewBloodBank(BloodBankRequest request) {
        BloodBank bloodBank = new BloodBank();
        bloodBank.setName(request.getName());

        bloodBankRepository.save(bloodBank);
    }

    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepository.findAll();
    }

    public void updateBloodBank(Long id, BloodBankRequest request) {
        BloodBank bloodBank = bloodBankRepository.findById(id).orElse(null);
        if(bloodBank != null){
            bloodBank.setName(request.getName());
            bloodBankRepository.save(bloodBank);
        }
    }

    public void deleteBloodBank(Long id) {
        if(bloodBankRepository.existsById(id))
            bloodBankRepository.deleteById(id);
    }
}
