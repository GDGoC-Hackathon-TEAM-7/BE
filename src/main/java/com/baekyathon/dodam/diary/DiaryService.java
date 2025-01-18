package com.baekyathon.dodam.diary;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baekyathon.dodam.DiaryRecord.DiaryRecord;
import com.baekyathon.dodam.DiaryRecord.DiaryRecordResDto;
import com.baekyathon.dodam.baby.Baby;
import com.baekyathon.dodam.baby.BabyRepository;
import com.baekyathon.dodam.base.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.baekyathon.dodam.base.ErrorCode.BABY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final BabyRepository babyRepository;

    // 날짜 기반으로 diaryId 알아낸다.

    @Transactional
    public Long findDiaryIdByBabyIdAndDate(Long babyId, LocalDate date) {
        return diaryRepository.findByBabyIdAndDate(babyId, date)
                .map(Diary::getId)
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND)); // 존재하지 않는 경우 예외 처리
    }

    // Diary 생성
    @Transactional
    public DiaryResDto createDiary(DiaryReqDto diaryReqDto) {
        Baby baby = babyRepository.findById(diaryReqDto.babyId())
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND));

        // Diary 생성
        Diary diary = diaryReqDto.toEntity(baby);

        Diary savedDiary = diaryRepository.save(diary);

        return DiaryResDto.from(savedDiary);
    }

    @Transactional
    public DiaryResDto getDiaryAndRecordsByBabyIdAndDate(Long babyId, LocalDate date) {
        Baby baby = babyRepository.findById(babyId)
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND));

        Diary diary = diaryRepository.findByBabyIdAndDate(babyId, date)
                .orElseGet(() -> creatEmptyDiary(baby,date));

        List<DiaryRecord> list = diary.getRecordList() != null ? diary.getRecordList() : new ArrayList<>(); // null 방어

        List<DiaryRecordResDto> records = list
                .stream()
                .map(DiaryRecordResDto::from)
                .collect(Collectors.toList());

        return DiaryResDto.fromWithRecords(diary, records);
    }

    // 사용자가 입력한 날짜가 없을 경우 빈 다이어리 생성
    private Diary creatEmptyDiary(Baby baby, LocalDate date) {
        Diary newDiary = Diary.builder()
                .baby(baby)
                .date(date)
                .memo("")
                .build();
        return diaryRepository.save(newDiary);
    }

}
