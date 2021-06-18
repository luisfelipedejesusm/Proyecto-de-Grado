package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.DTOs.DonorRequestPayload;
import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Models.DonorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface DonorRequestRepository extends JpaRepository<DonorRequest, Long> {

    List<DonorRequest> findAllByUserId(Long userId);
}
