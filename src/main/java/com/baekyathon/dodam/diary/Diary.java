package com.baekyathon.dodam.diary;

import com.baekyathon.dodam.DiaryRecord.DiaryRecord;
import com.baekyathon.dodam.baby.Baby;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "diary")
@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Diary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "baby_id", nullable = false)
  private Baby baby;

  @Column(name = "date", nullable = false)
  private Date date;

  @Column(name = "memo")
  private String memo;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<DiaryRecord> records = new ArrayList<>();

}
