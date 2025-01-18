package com.baekyathon.dodam.diary;


import com.baekyathon.dodam.DiaryRecord.DiaryRecordResDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class DiaryResDto {
    private Long id;
    private Long babyId;
    private LocalDate date; // 날짜만
    private String memo;
    private List<DiaryRecordResDto> records = new ArrayList<>();


    public static DiaryResDto from(Diary diary) {
        return DiaryResDto.builder()
                .id(diary.getId())
                .babyId(diary.getBaby().getId())
                .date(diary.getDate())
                .memo(diary.getMemo())
                .records(diary.getRecordList() != null
                        ? diary.getRecordList().stream()
                        .map(DiaryRecordResDto::from)
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }

    // Diary와 DiaryRecord를 함께 변환
    public static DiaryResDto fromWithRecords(Diary diary, List<DiaryRecordResDto> records) {
        return DiaryResDto.builder()
                .id(diary.getId())
                .babyId(diary.getBaby().getId())
                .date(diary.getDate())
                .memo(diary.getMemo())
                .records(records)
                .build();
    }
}

