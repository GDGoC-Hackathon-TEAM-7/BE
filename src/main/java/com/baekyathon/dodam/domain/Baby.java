package com.baekyathon.dodam.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(name= "img_url")
    private String imgUrl;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Baby(String name, String gender, Date birth, String imgUrl, User user) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.imgUrl = imgUrl;
    }
}
