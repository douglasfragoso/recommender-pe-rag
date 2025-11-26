package com.vamu_rec_rag.demo.MultiAiService.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;

@Service
public class MyChatModelListener implements ChatModelListener{

    private static final Logger log = LoggerFactory.getLogger(MyChatModelListener.class);

    @Override
    public void onRequest(ChatModelRequestContext requestContext){
        log.info("onRequest(): {}", requestContext.chatRequest());
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext){
        log.info("onResponse(): {}", responseContext.chatResponse());
    }
    
    @Override
    public void onError(ChatModelErrorContext errorContext){
        log.info("onError(): {}", errorContext.error().getMessage());
    }
    
}
