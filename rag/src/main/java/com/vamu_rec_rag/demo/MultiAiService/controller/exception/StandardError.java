package com.vamu_rec_rag.demo.MultiAiService.controller.exception;

import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents the StandardError in the system")
public class StandardError {

    @Schema(description = "Timestamp of the error", example = "2024-04-27T12:34:56Z", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant timestamp;

    @Schema(description = "Status code of the error", example = "404", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @Schema(description = "Error description", example = "Not Found", requiredMode = Schema.RequiredMode.REQUIRED)
    private String error;

    @Schema(description = "Detailed error message", example = "The requested resource was not found", requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;
    
    @Schema(description = "Path where the error occurred", example = "/api/users/123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String path;

}