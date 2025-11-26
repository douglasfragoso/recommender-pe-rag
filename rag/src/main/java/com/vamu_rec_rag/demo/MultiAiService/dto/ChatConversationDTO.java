package com.vamu_rec_rag.demo.MultiAiService.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Chat Conversation Data Transfer Object")
public class ChatConversationDTO {

    @Schema(
        description = "Identifier of the chat conversation", 
        example = "Explique o que é machine learning",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long id;

    @Schema(
        description = "Timestamp of the local data", 
        example = "2024-04-27T12:34:56Z",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Instant localData;

    @Schema(
        description = "List of user messages", 
        example = "[\"O que é inteligência artificial?\", \"Explique o que é machine learning\"]",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<String> messages = new ArrayList<>();

    @Schema(
        description = "List of AI responses", 
        example = "[\"Inteligência artificial é...\", \"Machine learning é...\"]",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<String> responses = new ArrayList<>();

    @Schema(
        description = "Name of the AI model used", 
        example = "deepseek-chat",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String modelName;

}
