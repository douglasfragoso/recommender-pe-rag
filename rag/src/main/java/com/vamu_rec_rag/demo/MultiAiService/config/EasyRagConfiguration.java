package com.vamu_rec_rag.demo.MultiAiService.config;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15q.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

@Configuration
public class EasyRagConfiguration {

    @Autowired
    private DocumentSplitter documentSplitter;

    @Bean
    public EmbeddingModel embeddingModel() {
        return new BgeSmallEnV15QuantizedEmbeddingModel();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore(
            EmbeddingModel embeddingModel, 
            ResourceLoader resourceLoader) throws IOException {
        
        List<Document> documents = loadDocuments(resourceLoader);
        
        logDocumentStats(documents);
        
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentSplitter)
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(documents);
        
        System.out.println("✅ Documentos processados com DocumentSplitter otimizado");
        
        return embeddingStore;
    }

    private List<Document> loadDocuments(ResourceLoader resourceLoader) throws IOException {
        List<Document> documents;
        
        try {
            Resource resource = resourceLoader.getResource("classpath:documents");
            Path documentsDir = Paths.get(resource.getURI());
            documents = FileSystemDocumentLoader.loadDocumentsRecursively(documentsDir);
        } catch (Exception e) {
            try {
                documents = FileSystemDocumentLoader.loadDocumentsRecursively(
                    Paths.get("src/main/resources/documents")
                );
            } catch (Exception ex) {
                documents = FileSystemDocumentLoader.loadDocumentsRecursively(
                    Paths.get("./documents")
                );
            }
        }
        
        return documents;
    }

    private void logDocumentStats(List<Document> documents) {
        System.out.println("📚 Documentos carregados: " + documents.size());
        
        int totalChars = 0;
        int totalWords = 0;
        
        for (Document doc : documents) {
            String fileName = doc.metadata().getString("file_name");
            String text = doc.text();
            int charCount = text.length();
            int wordCount = text.split("\\s+").length;
            
            totalChars += charCount;
            totalWords += wordCount;
            
            System.out.println("   📄 " + fileName + 
                " - " + charCount + " chars, " + wordCount + " words, " +
                "~" + (charCount / 500) + " chunks estimados");
        }
        
        System.out.println("📊 Estatísticas totais:");
        System.out.println("   • Total de caracteres: " + totalChars);
        System.out.println("   • Total de palavras: " + totalWords);
        System.out.println("   • Chunks estimados: ~" + (totalChars / 500));
    }

    @Bean
    public ContentRetriever contentRetriever(
            EmbeddingStore<TextSegment> embeddingStore, 
            EmbeddingModel embeddingModel) {
        
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(5) 
                .minScore(0.3) 
                .build();
    }
}