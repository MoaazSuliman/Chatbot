package com.moaaz.chatbot.AI;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin("*")
public class ChatBotController {


    private final ChatBotServiceImp chatBotServiceImp;

    public ChatBotController(ChatBotServiceImp chatBotServiceImp) {
        this.chatBotServiceImp = chatBotServiceImp;
    }

    @GetMapping("/ask")
    public ResponseEntity<?>ask(@RequestParam String question){
        return new ResponseEntity<>(chatBotServiceImp.getResponseForQuestion(question)  , HttpStatus.OK);
    }


}
