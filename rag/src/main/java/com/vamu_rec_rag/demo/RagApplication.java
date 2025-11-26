package com.vamu_rec_rag.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title = "Multi AI Assistant API", 
    version = "1.0", 
    description = "Sistema de assistente de IA com múltiplos provedores (DeepSeek, Gemini, Ollama) integrando RAG (Retrieval-Augmented Generation) e streaming em tempo real",
    contact = @Contact(
        name = "Douglas Fragoso", 
        email = "douglas.iff@gmail.com", 
        url = "https://github.com/douglasfragoso"
    )
))
public class RagApplication {

    public static void main(String[] args) {
        SpringApplication.run(RagApplication.class, args);
    }
}