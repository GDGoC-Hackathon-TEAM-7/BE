package com.baekyathon.dodam.chatbot;

import com.baekyathon.dodam.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("ai/chatbot")
public class AiChatController {
    private final AiChatService aiChatService;

    @PostMapping("/talk")
    @Operation(summary = "챗봇과 대화하기", description = "사용자의 메시지를 전달하여 챗봇의 응답을 반환합니다.")
    public BaseResponse<?> talkToChatbot(@RequestParam(name = "email") String email,
                                         @RequestParam(name = "message") String message) {
        return aiChatService.getChatResponse(email, message);
    }
}
