package com.baekyathon.dodam.repository;

import com.baekyathon.dodam.domain.Baby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BabyRepository extends JpaRepository<Baby,Long> {
    Optional<Baby> findById(Long id);
}
