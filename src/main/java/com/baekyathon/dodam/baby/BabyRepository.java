package com.baekyathon.dodam.baby;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BabyRepository extends JpaRepository<Baby,Long> {
  Optional<Baby> findById(Long id);
}