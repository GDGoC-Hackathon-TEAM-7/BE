package com.baekyathon.dodam.dto.baby;

import com.baekyathon.dodam.domain.Baby;
import lombok.Builder;

import java.util.Date;

@Builder
public record BabyInfoDto(
        String name,
        String gender,
        Date birth
){
    public static BabyInfoDto from (Baby baby) {
        return BabyInfoDto.builder()
                .name(baby.getName())
                .gender(baby.getGender())
                .birth(baby.getBirth())
                .build();
    }

    public Baby toEntity(BabyInfoDto babyInfoDto) {
        return Baby.builder()
                .name(name)
                .birth(birth)
                .gender(gender)
                .build();
    }
}
