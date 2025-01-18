package com.baekyathon.dodam.chatbot;

import com.baekyathon.dodam.base.BaseResponse;
import com.baekyathon.dodam.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class AiChatService {

    @Value("${spring.openai.model}")
    private String model;

    @Value("${spring.openai.api.url}")
    private String apiURL;

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    /**
     * ChatGPT API와 통신하여 메시지에 대한 응답 생성
     */
    public BaseResponse<String> getChatResponse(String loginId, String message) {
        try {
            String enhancedMessage = buildRequestMessage(message);
            ChatGPTRequestDto request = new ChatGPTRequestDto(model, enhancedMessage);

            ChatGPTResponseDto response = callChatGPTApi(request);

            String responseContent = processResponse(response);

            return new BaseResponse<>(HttpStatus.CREATED, "챗봇 응답 성공", responseContent);

        } catch (Exception e) {
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 발생: " + e.getMessage(), null);
        }
    }

    /**
     * 요청 메시지 생성 (프롬프트 엔지니어링 적용)
     * - 사용자의 요청 메시지를 기반으로 정확한 응답을 얻기 위해 구체적인 프롬프트를 생성
     */
    private String buildRequestMessage(String userMessage) {
        // 예시로, 유아 교육에 대한 요청일 때 구체적이고 따뜻한 답변을 요청하는 프롬프트 작성
        return String.format("사용자 질문: '%s' \n\n답변: 아이에게 적합한 육아 팁을 200자 이내로 친절하고 따뜻한 톤으로 제공해 주세요.", userMessage);
    }

    /**
     * ChatGPT API 호출
     */
    private ChatGPTResponseDto callChatGPTApi(ChatGPTRequestDto request) {
        return restTemplate.postForObject(apiURL, request, ChatGPTResponseDto.class);
    }

    /**
     * API 응답 처리 및 검증
     */
    private String processResponse(ChatGPTResponseDto response) {
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "응답을 받을 수 없습니다.";
        }

        String content = response.getChoices().get(0).getMessage().getContent();
        return content.length() > 400 ? content.substring(0, 400) + "..." : content;
    }
}
