package com.umutyerli.kisisel_asistan.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umutyerli.kisisel_asistan.dto.AIRequest;
import com.umutyerli.kisisel_asistan.dto.AIResponse;
import com.umutyerli.kisisel_asistan.services.AIService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/ai")
@RequiredArgsConstructor
public class AIController { // controller katmanı React'tan gelen isteği alır, servise yollar, sonucu döner

    private final AIService aiService;

    @PostMapping("/detect")
    public AIResponse detectText(@RequestBody AIRequest request){
        return aiService.detectText(request);
    }

 

}
