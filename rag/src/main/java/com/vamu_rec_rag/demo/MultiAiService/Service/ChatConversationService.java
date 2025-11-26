package com.vamu_rec_rag.demo.MultiAiService.Service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamu_rec_rag.demo.MultiAiService.dto.ChatConversationDTO;
import com.vamu_rec_rag.demo.MultiAiService.model.ChatConversation;
import com.vamu_rec_rag.demo.MultiAiService.repository.ChatConversationRepository;

@Service
public class ChatConversationService {
    
    @Autowired
    private ChatConversationRepository chatConversationRepository;

    @Transactional
    public void saveConversation(String userMessage, String aiResponse, String modelName) {

        ChatConversation conversation = new ChatConversation();
        conversation.setLocalData(Instant.now());
        conversation.setMessages(List.of(userMessage));
        conversation.setResponses(List.of(aiResponse));
        conversation.setModelName(modelName);

        chatConversationRepository.save(conversation);
    }

    @Transactional(readOnly = true)
    public Page<ChatConversationDTO> getAllConversations(Pageable pageable) {
        Page<ChatConversation> list = chatConversationRepository.findAll(pageable);
        return list.map(x -> new ChatConversationDTO(x.getId(),
                x.getLocalData(),
                x.getMessages(),
                x.getResponses(),
                x.getModelName()));
    }

    @Transactional(readOnly = true)
    public ChatConversationDTO getConversationById(Long id) {
        ChatConversation conversation = chatConversationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversation not found with id: " + id));
        return new ChatConversationDTO(conversation.getId(),
                conversation.getLocalData(),
                conversation.getMessages(),
                conversation.getResponses(),
                conversation.getModelName());
    }
    
}
