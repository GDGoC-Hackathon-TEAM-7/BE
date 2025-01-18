package com.baekyathon.dodam.DiaryRecord;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
public record DiaryRecordResDto (

        Long id,
        Long diaryId,
        LocalDateTime timestamp,
        DiaryRecord.Category category
){

    public static DiaryRecordResDto from(DiaryRecord record) {
        return DiaryRecordResDto.builder()
                .id(record.getId())
                .diaryId(record.getDiary() != null ? record.getDiary().getId() : null)
                .timestamp(record.getTimestamp())
                .category(record.getCategory())
                .build();
    }

}

