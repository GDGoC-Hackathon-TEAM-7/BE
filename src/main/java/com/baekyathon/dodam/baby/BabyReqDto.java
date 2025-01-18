package com.baekyathon.dodam.baby;

import com.baekyathon.dodam.user.User;

import java.time.LocalDate;

public record BabyReqDto (
        String name,
        String gender,
        LocalDate birth,
        Long userId
){
    public Baby toEntity(User user) {
        return Baby.builder()
                .name(name)
                .birth(birth)
                .gender(gender)
                .user(user)
                .build();
    }
}