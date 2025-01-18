package com.baekyathon.dodam.baby;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record BabyInfoDto(
        String name,
        String gender,
        LocalDate birth,
        String profileImg
){
    public static BabyInfoDto from (Baby baby) {
        return BabyInfoDto.builder()
                .name(baby.getName())
                .gender(baby.getGender())
                .birth(baby.getBirth())
                .profileImg(baby.getProfileImg())
                .build();
    }
}
