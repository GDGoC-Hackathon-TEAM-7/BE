package com.baekyathon.dodam.baby;

import com.baekyathon.dodam.base.CustomException;
import com.baekyathon.dodam.user.User;
import com.baekyathon.dodam.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.baekyathon.dodam.base.ErrorCode.BABY_NOT_FOUND;
import static com.baekyathon.dodam.base.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class BabyService {

    private final BabyRepository babyRepository;
    private final UserRepository userRepository;

    @Transactional
    public BabyInfoDto addBaby(BabyReqDto babyReqDto) {
        // User 조회
        User user = userRepository.findById(babyReqDto.userId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Baby baby = babyReqDto.toEntity(user);
        babyRepository.save(baby);
        return BabyInfoDto.from(baby);
    }

    @Transactional(readOnly = true)
    public BabyInfoDto findBabyById(Long babyId) {
        return babyRepository.findById(babyId)
                .map(BabyInfoDto::from) // 바로 DTO 변환
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND));
    }

    @Transactional
    public BabyInfoDto update(Long babyId, BabyInfoDto babyInfoDto) {
        Baby baby = babyRepository.findById(babyId)
                .orElseThrow(() -> new CustomException(BABY_NOT_FOUND));
        baby.update(babyInfoDto.name(), babyInfoDto.gender(), babyInfoDto.birth(),babyInfoDto.profileImg());
        return BabyInfoDto.from(baby);
    }

    @Transactional
    public void delete(Long babyId) {
        babyRepository.deleteById(babyId);
    }


}
