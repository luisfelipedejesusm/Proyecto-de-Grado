package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.Models.DonorRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRequestRepository extends JpaRepository<DonorRequest, Long> {

    List<DonorRequest> findAllByUserId(Long userId);
}
