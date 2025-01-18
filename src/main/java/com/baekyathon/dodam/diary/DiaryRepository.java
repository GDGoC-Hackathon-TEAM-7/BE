package com.baekyathon.dodam.diary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
  Optional<Diary> findByBabyIdAndDate(Long babyId, LocalDate date);

  @Query(value = "SELECT id FROM diary " +
          "WHERE baby_id = :babyId " +
          "AND date = :date " +
          "ORDER BY id DESC LIMIT 1", nativeQuery = true)
  Optional<Long> findDiaryIdByBabyIdAndDate(@Param("babyId") Long babyId, @Param("date") LocalDate date);

}
