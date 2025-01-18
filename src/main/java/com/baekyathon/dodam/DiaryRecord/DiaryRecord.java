package com.baekyathon.dodam.DiaryRecord;

import com.baekyathon.dodam.diary.Diary;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "diary_record")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime timestamp; // 기록 시간

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category; // Enum 타입 (식사, 수면 등)

  @ManyToOne
  @JoinColumn(name = "diary_id", nullable = false)
  private Diary diary;

  public enum Category {
    식사,
    기저귀,
    수면,
    기상,
  }

  public DiaryRecord(Diary diary, Category category) {
    this.diary = diary;
    this.category = category;
    this.timestamp = LocalDateTime.now(); // 현재 시각 자동 설정
  }

}
