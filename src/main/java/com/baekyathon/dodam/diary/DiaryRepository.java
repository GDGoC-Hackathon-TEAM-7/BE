package com.baekyathon.dodam.diary;

import com.baekyathon.dodam.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
//  Optional<Diary> findByBabyId(Long babyId);

  Optional<Diary> findByBabyIdAndDate(Long babyId, String date);
}
