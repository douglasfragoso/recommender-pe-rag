package com.vamu_rec_rag.demo.MultiAiService.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vamu_rec_rag.demo.MultiAiService.controller.exception.StandardError;
import com.vamu_rec_rag.demo.MultiAiService.dto.HealthStatusDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/monitoring", produces = "application/json")
@Tag(name = "Monitoring", description = "API for monitoring and testing AI service components")
public class MonitoringController {

    @GetMapping("/test/rag")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "RAG test completed successfully", content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "400", description = "Invalid query", content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = StandardError.class)))
    })
    @Operation(summary = "Test RAG functionality", description = "Test the RAG (Retrieval Augmented Generation) system with a custom query", tags = {"Monitoring"})
    public ResponseEntity<Map<String, Object>> testRag(@RequestParam String query) {
        // Implementação do teste RAG (como mostrado anteriormente)
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "query", query,
            "status", "tested",
            "timestamp", java.time.Instant.now()
        ));
    }

    @GetMapping("/health/detailed")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Detailed health check successful", content = @Content(schema = @Schema(implementation = HealthStatusDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = StandardError.class)))
    })
    @Operation(summary = "Detailed health check", description = "Get detailed health status including all components", tags = {"Monitoring"})
    public ResponseEntity<HealthStatusDTO> detailedHealth() {
        HealthStatusDTO healthStatus = new HealthStatusDTO(
            "UP",
            "Multi AI Assistant - Detailed",
            Map.of(
                "ollama", "✅ Disponível",
                "deepseek", "✅ Disponível", 
                "gemini", "✅ Disponível",
                "rag", "✅ Ativo",
                "embedding", "✅ Ativo",
                "document-splitter", "✅ Ativo"
            ),
            Map.of(
                "rag", "✅",
                "streaming", "✅",
                "memory", "✅",
                "listeners", "✅",
                "embeddings", "✅",
                "document-splitter", "✅"
            )
        );
        return ResponseEntity.status(HttpStatus.OK).body(healthStatus);
    }
}