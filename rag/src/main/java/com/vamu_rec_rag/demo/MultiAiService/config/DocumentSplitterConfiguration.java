package com.vamu_rec_rag.demo.MultiAiService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;

@Configuration
public class DocumentSplitterConfiguration {

    /**
     * DocumentSplitter otimizado para RAG
     * - Chunks de 500 caracteres (ideal para modelos de embedding)
     * - Overlap de 50 caracteres para manter contexto
     * - Estratégia recursiva que respeita parágrafos naturais
     */
    @Bean
    public DocumentSplitter documentSplitter() {
        return DocumentSplitters.recursive(
            500,    
            50,     
            null    
        );
    }
    
    /**
     * Alternativa: DocumentSplitter para documentos grandes
     * - Chunks maiores para documentos técnicos
     * - Mais overlap para contexto complexo
     */
    @Bean
    public DocumentSplitter largeDocumentSplitter() {
        return DocumentSplitters.recursive(
            1000,   
            100,    
            null
        );
    }
}