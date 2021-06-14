package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.Models.ERole;
import com.luisfelipedejesusm.final_project.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
