package com.moaaz.chatbot.AI;

import org.springframework.stereotype.Service;

@Service
public interface ChatBotService {

    String getResponseForQuestion(String question);
}
