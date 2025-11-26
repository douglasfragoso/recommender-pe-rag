package com.vamu_rec_rag.demo.MultiAiService;

import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

public interface StreamingAssistant {

    @SystemMessage(fromResource = "prompt.md")
    Flux<String> chat(String userMessage);
}

