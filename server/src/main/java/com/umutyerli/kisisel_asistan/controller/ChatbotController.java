package com.umutyerli.kisisel_asistan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umutyerli.kisisel_asistan.dto.ChatRequest;
import com.umutyerli.kisisel_asistan.services.ChatbotService;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService){
        this.chatbotService=chatbotService;
    }

    @PostMapping
    public ResponseEntity<String> sohbetEt(@RequestBody ChatRequest chatRequest){
        String yanit = chatbotService.sohbetEt(chatRequest.getMesaj());
        return ResponseEntity.ok(yanit);

    }
}
