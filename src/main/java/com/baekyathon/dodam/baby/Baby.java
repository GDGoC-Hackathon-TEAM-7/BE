package com.baekyathon.dodam.baby;
import com.baekyathon.dodam.diary.Diary;
import com.baekyathon.dodam.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name="baby")
@Entity
@Getter
@NoArgsConstructor
public class Baby {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name="name", nullable = false)
  private String name;

  @Column(name="gender", nullable = false)
  private String gender;

  @Column(name="birth", nullable = false)
  private Date birth;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="user_id")
  private User user;

  @OneToMany(mappedBy = "baby", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  List<Diary> diaryList = new ArrayList<>();

  @Builder
  public Baby(String name, String gender, Date birth, User user) {
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.user = user;
  }

  public void update(String name, String gender, Date birth) {
    this.name = name;
    this.gender = gender;
    this.birth = birth;
  }


}