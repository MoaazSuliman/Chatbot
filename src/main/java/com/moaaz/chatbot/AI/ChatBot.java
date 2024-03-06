package com.moaaz.chatbot.AI;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ChatBot {

    private List<String> patterns;
    private List<String> responses;

}
