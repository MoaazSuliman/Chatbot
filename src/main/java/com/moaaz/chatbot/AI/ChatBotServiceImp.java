package com.moaaz.chatbot.AI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ChatBotServiceImp implements ChatBotService {

    private String JSON_FILE_PATH = "AI/ChatBot.json";


    @Override
    public String getResponseForQuestion(String question) {
        List<ChatBot> chatBots = readChatBotsFromJsonFile(); // get list of all chatbots  patterns and responses from json file
        ChatBot chatBotThatHasThisQuestion = getChatBotThatHasThisQuestion(chatBots, question);// get specific chatbot that has responses for the question....
        return getRandomAnswerFromChatBot(chatBotThatHasThisQuestion); // return random response


    }
    private String getRandomAnswerFromChatBot(ChatBot chatBotToExtractRandomAnswerFromIt) {
        Random random = new Random();
        int index = random.nextInt(chatBotToExtractRandomAnswerFromIt.getResponses().size());
        return chatBotToExtractRandomAnswerFromIt.getResponses().get(index);
    }

    private List<ChatBot> readChatBotsFromJsonFile() {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(JSON_FILE_PATH);
            List<ChatBot> chatBots = objectMapper.readValue(inputStream, new TypeReference<List<ChatBot>>() {
            });
            inputStream.close();
            return chatBots;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private ChatBot getChatBotThatHasThisQuestion(List<ChatBot> chatBots, String question) {
        for (ChatBot chatBot : chatBots) {
            List<String> questions = chatBot.getPatterns();
            Optional<String> any = questions.stream().filter(q -> q.contains(question)).findAny();
            if (any.isPresent()) {
                return chatBot;
            }
        }
        throw new RuntimeException("This Question Is Not Supported...");
    }
}
