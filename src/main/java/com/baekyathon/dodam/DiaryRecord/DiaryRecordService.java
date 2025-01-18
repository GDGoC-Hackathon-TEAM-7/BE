package com.baekyathon.dodam.DiaryRecord;


import com.baekyathon.dodam.baby.Baby;
import com.baekyathon.dodam.baby.BabyRepository;
import com.baekyathon.dodam.diary.Diary;
import com.baekyathon.dodam.diary.DiaryRepository;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryRecordService {

    //기록 저장하기
    private final DiaryRecordRepository diaryRecordRepository;
    private final DiaryRepository diaryRepository;
    private final BabyRepository babyRepository;



    public DiaryRecordDTO saveDiaryRecord(Long babyId, String category, LocalDateTime date) {
        // Baby와 관련된 Diary를 찾거나 생성 (여기선 날짜로 조회 가능)
        Diary diary = diaryRepository.findByBabyIdAndDate(babyId, date)
            .orElseGet(() -> createNewDiary(babyId, String.valueOf(date))); // 없으면 새로 생성

        // 새로운 DiaryRecord 생성
        DiaryRecord diaryRecord = new DiaryRecord();
        diaryRecord.setCategory(DiaryRecord.Category.valueOf(category));
        diaryRecord.setTimestamp(LocalDateTime.now());  // 현재 시간으로 기록
        diaryRecord.setDiary(diary);  // 해당 diary와 연결

        // DiaryRecord 저장
        DiaryRecord savedRecord = diaryRecordRepository.save(diaryRecord);

        return new DiaryRecordDTO(savedRecord.getTimestamp(), savedRecord.getCategory().name());
    }

    private Diary createNewDiary(Long babyId, String date) {
        Baby baby = babyRepository.findById(babyId)
            .orElseThrow(() -> new IllegalArgumentException("Baby not found"));

        Diary diary = new Diary();
        diary.setBaby(baby);
        diary.setDate(LocalDateTime.parse(date)); // 날짜 설정

        return diaryRepository.save(diary);
    }

}
