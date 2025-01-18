package com.baekyathon.dodam.diary;


import com.baekyathon.dodam.DiaryRecord.DiaryRecordDTO;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    // 날짜 별 전체 기록 조회
    public List<DiaryRecordDTO> getDiaryRecordsByDate(Long diaryId, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      // Find diary by ID and filter records by the given date
      Diary diary = diaryRepository.findById(diaryId)
          .orElseThrow(() -> new IllegalArgumentException("Diary not found"));

      return diary.getRecords().stream()
          .filter(record -> dateFormat.format(record.getTimestamp()).equals(date))
          .map(record -> new DiaryRecordDTO(
              record.getTimestamp(),
              record.getCategory().name() // Enum을 String으로 변환
          ))
          .collect(Collectors.toList());
    }

    // 사진 첨부


    // 메모 추가

}
