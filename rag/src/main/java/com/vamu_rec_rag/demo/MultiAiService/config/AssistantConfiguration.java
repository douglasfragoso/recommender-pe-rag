package com.vamu_rec_rag.demo.MultiAiService.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.vamu_rec_rag.demo.MultiAiService.StreamingAssistant;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;

@Configuration
public class AssistantConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    public StreamingAssistant deepSeekAssistant(
            @Qualifier("deepseekStreamingChatModel") StreamingChatModel deepseekStreamingChatModel,
            ContentRetriever contentRetriever,
            ChatMemory chatMemory
    ) {
        return AiServices.builder(StreamingAssistant.class)
                .streamingChatModel(deepseekStreamingChatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(chatMemory)
                .build();
    }

    @Bean
    public StreamingAssistant geminiAssistant(
            @Qualifier("geminiStreamingChatModel") StreamingChatModel geminiStreamingChatModel,
            ContentRetriever contentRetriever,
            ChatMemory chatMemory
    ) {
        return AiServices.builder(StreamingAssistant.class)
                .streamingChatModel(geminiStreamingChatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(chatMemory)
                .build();
    }

    @Bean
    public StreamingAssistant ollamaAssistant( 
            @Qualifier("ollamaStreamingChatModel") StreamingChatModel ollamaStreamingChatModel, 
            ContentRetriever contentRetriever,
            ChatMemory chatMemory
    ) {
        return AiServices.builder(StreamingAssistant.class)
                .streamingChatModel(ollamaStreamingChatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(chatMemory)
                .build();
    }
}