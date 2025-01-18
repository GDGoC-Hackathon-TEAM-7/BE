package com.baekyathon.dodam.diary;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
//  Optional<Diary> findByBabyId(Long babyId);

  Optional<Diary> findByBabyIdAndDate(Long baby_id, LocalDateTime date);
}
