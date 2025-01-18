package com.baekyathon.dodam.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    public User createUser(SignUpDTO signUpDTO) {
        User user = signUpDTO.toEntity();
        return userRepository.save(user);
    }

    // 로그인 인증
    public boolean authenticateUser(String email, String password) {
        Optional<User> userOpt = Optional.ofNullable(userRepository.findByEmail(email));

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
