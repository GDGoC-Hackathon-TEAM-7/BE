package com.baekyathon.dodam.baby;

import com.baekyathon.dodam.user.User;

import java.util.Date;

public record BabyReqDto (
        String name,
        String gender,
        Date birth,
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