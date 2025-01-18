package com.baekyathon.dodam.diary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
//  @Query("SELECT d FROM Diary d WHERE d.baby.id = :babyId AND d.date = :date")
//  Optional<Diary> findByBabyIdAndDate(@Param("babyId") Long babyId, @Param("date") LocalDate date);

  @Query("SELECT d FROM Diary d JOIN FETCH d.recordList WHERE d.baby.id = :babyId AND DATE(d.date) = :date")
  Optional<Diary> findByBabyIdAndDate(@Param("babyId") Long babyId, @Param("date") LocalDate date);

  @Query(value = "SELECT id FROM diary " +
          "WHERE baby_id = :babyId " +
          "AND date = :date " +
          "ORDER BY id DESC LIMIT 1", nativeQuery = true)
  Optional<Long> findDiaryIdByBabyIdAndDate(@Param("babyId") Long babyId, @Param("date") LocalDate date);

}
