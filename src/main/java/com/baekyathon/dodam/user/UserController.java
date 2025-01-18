package com.baekyathon.dodam.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "회원", description = "회원 관련 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "사용자가 회원가입을 할 수 있는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청: 입력값 오류",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpDTO signUpDTO) {
        User newUser = userService.createUser(signUpDTO);
        return ResponseEntity.ok(newUser);
    }

    @Operation(summary = "로그인", description = "사용자가 로그인할 수 있는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "로그인 실패: 이메일 또는 비밀번호 오류",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }
}
