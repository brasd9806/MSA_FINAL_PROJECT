package com.sparta.msa.ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OllamaRequest {
    private String model;
    private String prompt;
    private boolean stream;
}
