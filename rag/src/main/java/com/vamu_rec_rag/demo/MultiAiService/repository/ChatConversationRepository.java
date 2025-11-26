package com.vamu_rec_rag.demo.MultiAiService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vamu_rec_rag.demo.MultiAiService.model.ChatConversation;

@Repository
public interface ChatConversationRepository extends JpaRepository<ChatConversation, Long>{
    
}
