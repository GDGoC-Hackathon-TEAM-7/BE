package com.baekyathon.dodam.baby;
import com.baekyathon.dodam.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Baby(String name, String gender, Date birth) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }

    public void update(String name, String gender, Date birth) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }


}
