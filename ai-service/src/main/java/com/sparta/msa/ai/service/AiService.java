package com.sparta.msa.ai.service;

import com.sparta.msa.ai.config.OllamaConfig;
import com.sparta.msa.ai.dto.OllamaRequest;
import com.sparta.msa.ai.dto.OllamaResponse;
import com.sparta.msa.ai.dto.ProductRecommendRequest;
import com.sparta.msa.ai.dto.ReviewAnalyzeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AiService {

    private final RestTemplate restTemplate;
    private final OllamaConfig ollamaConfig;

    public String analyzeReview(ReviewAnalyzeRequest request) {
        String prompt = String.format(
            "다음 상품 리뷰의 감성을 분석해줘. 반드시 '긍정', '부정', '중립' 중 하나로만 답하고, 한 줄로 이유를 설명해.\n\n리뷰: %s",
            request.getReviewText()
        );
        return callOllama(prompt);
    }

    public String recommendProduct(ProductRecommendRequest request) {
        int count = request.getCount() != null ? request.getCount() : 3;
        String prompt = String.format(
            "쇼핑몰 상품 추천 도우미야. 다음 조건에 맞는 상품을 %d가지 추천하고 각각 이유를 간단히 설명해줘. 한국어로 답해.\n\n카테고리: %s\n키워드: %s\n예산: %s원",
            count,
            request.getCategory(),
            request.getKeyword() != null ? request.getKeyword() : "없음",
            request.getBudget() != null ? request.getBudget() : "제한없음"
        );
        return callOllama(prompt);
    }

    private String callOllama(String prompt) {
        OllamaRequest ollamaRequest = OllamaRequest.builder()
                .model(ollamaConfig.getOllamaModel())
                .prompt(prompt)
                .stream(false)
                .build();

        OllamaResponse response = restTemplate.postForObject(
                ollamaConfig.getOllamaUrl() + "/api/generate",
                ollamaRequest,
                OllamaResponse.class
        );

        return response != null ? response.getResponse() : "AI 응답을 받지 못했습니다.";
    }
}
