package com.baekyathon.dodam.DiaryRecord;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryRecordDTO {
    private LocalDateTime timestamp;
    private String category;

    public DiaryRecordDTO(LocalDateTime timestamp, String category) {
        this.timestamp = timestamp;
        this.category = category;
    }
}
