package com.vamu_rec_rag.demo.MultiAiService.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.segment.TextSegment;

@Service
public class DocumentSplitterListener {

    private static final Logger log = LoggerFactory.getLogger(DocumentSplitterListener.class);

    public void logSplittingProcess(Document document, DocumentSplitter splitter, List<TextSegment> segments) {
        log.info("✂️  Documento dividido: '{}' em {} segmentos", 
            document.metadata().getString("file_name"), 
            segments.size());
        
        for (int i = 0; i < segments.size(); i++) {
            TextSegment segment = segments.get(i);
            log.debug("   📍 Segmento {}: {} caracteres", 
                i + 1, segment.text().length());
        }
    }
}