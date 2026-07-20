package com.sparta.msa.review.dto.request;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequest {

    @NotNull
    @Min(1)
    @Max(5)
    Integer rating;

    @NotBlank
    @Size(max = 500)
    String content;
}
