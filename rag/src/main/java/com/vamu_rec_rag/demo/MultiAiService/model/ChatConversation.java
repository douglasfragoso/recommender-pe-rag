package com.vamu_rec_rag.demo.MultiAiService.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatConversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private Instant localData;

    // Persiste a lista de mensagens do usuário em uma tabela separada
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_chat_messages", joinColumns = @JoinColumn(name = "conversation_id"))
    @Column(name = "message_text", columnDefinition = "TEXT")
    private List<String> messages = new ArrayList<>();

    // Persiste a lista de respostas da IA em uma tabela separada
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_chat_responses", joinColumns = @JoinColumn(name = "conversation_id"))
    @Column(name = "response_text", columnDefinition = "TEXT")
    private List<String> responses = new ArrayList<>();

    @Column(nullable = false)
    private String modelName;
    
}
