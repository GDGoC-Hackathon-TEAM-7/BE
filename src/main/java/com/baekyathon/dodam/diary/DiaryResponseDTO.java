package com.baekyathon.dodam.diary;


import com.baekyathon.dodam.DiaryRecord.DiaryRecordDTO;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryResponseDTO {

    private String date;
    private List<DiaryRecordDTO> records;

    public DiaryResponseDTO(String date, List<DiaryRecordDTO> records) {
        this.date = date;
        this.records = records;
    }


}
