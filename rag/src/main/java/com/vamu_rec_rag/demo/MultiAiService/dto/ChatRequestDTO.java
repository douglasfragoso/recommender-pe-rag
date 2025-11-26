package com.vamu_rec_rag.demo.MultiAiService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload for chat streaming")
public class ChatRequestDTO {

    @Schema(
        description = "Message to send to the AI assistant", 
        example = "Explique o que é machine learning",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String message;
}