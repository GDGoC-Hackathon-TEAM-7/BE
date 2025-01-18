package com.baekyathon.dodam.service;

import com.baekyathon.dodam.base.CustomException;
import com.baekyathon.dodam.domain.Baby;
import com.baekyathon.dodam.dto.baby.BabyInfoDto;
import com.baekyathon.dodam.repository.BabyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.baekyathon.dodam.base.ErrorCode.BABY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class BabyService {

    private final BabyRepository babyRepository;

    @Transactional
    public BabyInfoDto addBaby(BabyInfoDto babyInfoDto) {
        Baby baby = babyInfoDto.toEntity(babyInfoDto);
        babyRepository.save(baby);
        return BabyInfoDto.from(baby);
    }

    @Transactional(readOnly = true)
    public BabyInfoDto findBabyById(Long babyId) {
        Baby baby = babyRepository.findById(babyId)
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND));
        return BabyInfoDto.from(baby);
    }


}
