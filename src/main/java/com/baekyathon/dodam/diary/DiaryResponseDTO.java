package com.baekyathon.dodam.diary;


import com.baekyathon.dodam.DiaryRecord.DiaryRecordDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryResponseDTO {

    private LocalDateTime date;
    private List<DiaryRecordDTO> records;

    public DiaryResponseDTO(LocalDateTime date,List<DiaryRecordDTO> records) {
        this.records = records;
        this.date = date;
    }
}
