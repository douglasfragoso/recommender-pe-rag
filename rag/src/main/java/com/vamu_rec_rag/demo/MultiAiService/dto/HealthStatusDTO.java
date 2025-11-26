package com.vamu_rec_rag.demo.MultiAiService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Health status response")
public class HealthStatusDTO {

    @Schema(description = "Service status", example = "UP")
    private String status;
    
    @Schema(description = "Service name", example = "Multi AI Assistant")
    private String service;
    
    @Schema(description = "Available AI models")
    private Map<String, String> models;
    
    @Schema(description = "Enabled features")
    private Map<String, String> features;
}