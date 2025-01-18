package com.baekyathon.dodam.baby;

import com.baekyathon.dodam.user.User;

import java.time.LocalDate;

public record BabyReqDto (
        String name,
        String gender,
        LocalDate birth,
        String email,
        String profileImg
){
    public Baby toEntity(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null when creating a Baby entity");
        }
        return Baby.builder()
                .name(name)
                .birth(birth)
                .gender(gender)
                .profileImg(profileImg)
                .email(user.getEmail())
                .user(user)
                .build();
    }
}