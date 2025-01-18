package com.baekyathon.dodam.diary;

import com.baekyathon.dodam.DiaryRecord.DiaryRecord;
import com.baekyathon.dodam.baby.Baby;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "diary")
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "memo")
  private String memo;

  @ManyToOne
  @JoinColumn(name = "baby_id")
  private Baby baby;

  @OneToMany(mappedBy = "diary", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<DiaryRecord> recordList = new ArrayList<>();

}
