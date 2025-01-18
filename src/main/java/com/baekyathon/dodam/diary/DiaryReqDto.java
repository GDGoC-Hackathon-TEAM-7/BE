package com.baekyathon.dodam.diary;


import com.baekyathon.dodam.baby.Baby;

import java.time.LocalDate;

public record DiaryReqDto (
        Long babyId,
        LocalDate date,
        String memo
){
    public Diary toEntity(Baby baby) {
        return Diary.builder()
                .baby(baby)
                .date(date)
                .memo(memo)
                .build();
    }
}
