package com.baekyathon.dodam.baby;

import com.baekyathon.dodam.base.CustomException;
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
        Baby baby = babyInfoDto.toEntity();
        babyRepository.save(baby);
        return BabyInfoDto.from(baby);
    }

    @Transactional(readOnly = true)
    public BabyInfoDto findBabyById(Long babyId) {
        Baby baby = babyRepository.findById(babyId)
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND));
        return BabyInfoDto.from(baby);
    }

    @Transactional
    public BabyInfoDto update(Long babyId, BabyInfoDto babyInfoDto) {
        Baby baby = babyRepository.findById(babyId)
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND));
        baby.update(babyInfoDto.name(), babyInfoDto.gender(), babyInfoDto.birth());
        return BabyInfoDto.from(baby);
    }

    @Transactional
    public void delete(Long babyId) {
        babyRepository.deleteById(babyId);
    }


}
