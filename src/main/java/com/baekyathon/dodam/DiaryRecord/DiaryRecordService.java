package com.baekyathon.dodam.DiaryRecord;

import com.baekyathon.dodam.base.CustomException;
import com.baekyathon.dodam.diary.Diary;
import com.baekyathon.dodam.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.baekyathon.dodam.base.ErrorCode.DIARY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DiaryRecordService {

    private final DiaryRecordRepository diaryRecordRepository;
    private final DiaryRepository diaryRepository;

    // DiaryRecord 생성
    @Transactional
    public DiaryRecordResDto createDiaryRecord(DiaryRecordReqDto diaryRecordReqDto) {
        Diary diary = diaryRepository.findById(diaryRecordReqDto.diaryId())
                .orElseThrow(() -> new CustomException(DIARY_NOT_FOUND));

        DiaryRecord record = new DiaryRecord(diary, diaryRecordReqDto.category());
        DiaryRecord savedDiaryRecord = diaryRecordRepository.save(record);
        return DiaryRecordResDto.from(savedDiaryRecord);
    }


}
