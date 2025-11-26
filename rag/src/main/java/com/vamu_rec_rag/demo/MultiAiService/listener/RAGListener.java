package com.vamu_rec_rag.demo.MultiAiService.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;

@Service
@Primary  
public class RAGListener implements ContentRetriever {

    private static final Logger log = LoggerFactory.getLogger(RAGListener.class);
    
    private final ContentRetriever delegate;

    public RAGListener(ContentRetriever delegate) {
        this.delegate = delegate;
        log.info("✅ RAGListener configurado");
    }

    @Override
    public List<Content> retrieve(Query query) {
        long startTime = System.currentTimeMillis();
        log.info("🔍 RAG - Buscando conteúdos para: '{}'", query.text());
        
        try {
            List<Content> contents = delegate.retrieve(query);
            long duration = System.currentTimeMillis() - startTime;
            
            log.info("✅ RAG - Encontrados {} conteúdos em {}ms", contents.size(), duration);
            
            return contents;
        } catch (Exception e) {
            log.error("❌ RAG - Erro na busca: {}", e.getMessage());
            throw e;
        }
    }
}