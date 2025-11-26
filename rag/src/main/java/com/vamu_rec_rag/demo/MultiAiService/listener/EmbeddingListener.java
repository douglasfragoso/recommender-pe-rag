package com.vamu_rec_rag.demo.MultiAiService.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;

@Service
@Primary  
public class EmbeddingListener implements EmbeddingStore<TextSegment> {

    private static final Logger log = LoggerFactory.getLogger(EmbeddingListener.class);
    
    private final EmbeddingStore<TextSegment> delegate;

    public EmbeddingListener(EmbeddingStore<TextSegment> delegate) {
        this.delegate = delegate;
        log.info("✅ EmbeddingListener configurado");
    }

    @Override
    public String add(Embedding embedding) {
        log.debug("📥 Adicionando embedding");
        return delegate.add(embedding);
    }

    @Override
    public void add(String id, Embedding embedding) {
        log.debug("📥 Adicionando embedding com ID: {}", id);
        delegate.add(id, embedding);
    }

    @Override
    public String add(Embedding embedding, TextSegment textSegment) {
        log.debug("📥 Adicionando embedding com segmento de texto");
        return delegate.add(embedding, textSegment);
    }

    @Override
    public List<String> addAll(List<Embedding> embeddings) {
        log.info("📦 Adicionando {} embeddings em lote", embeddings.size());
        return delegate.addAll(embeddings);
    }

    @Override
    public List<String> addAll(List<Embedding> embeddings, List<TextSegment> textSegments) {
        log.info("📦 Adicionando {} embeddings com {} segmentos", 
                embeddings.size(), textSegments.size());
        return delegate.addAll(embeddings, textSegments);
    }

    public List<EmbeddingMatch<TextSegment>> findRelevant(Embedding referenceEmbedding, int maxResults, double minScore) {
        long startTime = System.currentTimeMillis();
        log.debug("🔍 Buscando {} resultados com minScore {}", maxResults, minScore);
 
        List<EmbeddingMatch<TextSegment>> matches = ((EmbeddingListener) delegate).findRelevant(referenceEmbedding, maxResults, minScore);
        long duration = System.currentTimeMillis() - startTime;
        
        log.info("🎯 Encontrados {} resultados em {}ms", matches.size(), duration);
        
        for (EmbeddingMatch<TextSegment> match : matches) {
            String text = match.embedded().text();
            String preview = text.length() > 50 ? text.substring(0, 50) + "..." : text;
            log.debug("   → Score: {:.3f} - Texto: {}", match.score(), preview);
        }
        
        return matches;
    }

    @Override
    public EmbeddingSearchResult<TextSegment> search(EmbeddingSearchRequest request) {
        long startTime = System.currentTimeMillis();
        log.debug("🔍 [SEARCH] Buscando resultados - max: {}, minScore: {}", 
                 request.maxResults(), request.minScore());
        
        EmbeddingSearchResult<TextSegment> result = delegate.search(request);
        long duration = System.currentTimeMillis() - startTime;
        
        log.info("🎯 [SEARCH] Encontrados {} resultados em {}ms", 
                result.matches().size(), duration);
        
        return result;
    }
}