package com.baekyathon.dodam.DiaryRecord;

import com.baekyathon.dodam.diary.Diary;

public record DiaryRecordReqDto (
        Long diaryId,
        DiaryRecord.Category category
){

    public DiaryRecord toEntity(Diary diary) {
        return DiaryRecord.builder()
                .diary(diary)
                .category(category)
                .build();
    }
}
