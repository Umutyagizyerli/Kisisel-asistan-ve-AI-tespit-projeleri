package com.umutyerli.kisisel_asistan.services;

import org.springframework.stereotype.Service; // DTO,service,controller gibi kendi sınıflarımızı com.umutyerli.... ile tutarız
import org.springframework.web.client.RestTemplate;

import com.umutyerli.kisisel_asistan.dto.AIRequest; // bu gibi sınıflar springin resmi kütüphanelerinden gelir
import com.umutyerli.kisisel_asistan.dto.AIResponse;

//import org.springframework.web.client.RestTemplate;

@Service
public class AIService{

    public AIResponse detectText(AIRequest request){
        String pythonApiUrl = "http://localhost:5001/predict"; // Flask apı url

        RestTemplate restTemplate=new RestTemplate();
        AIResponse response = restTemplate.postForObject(pythonApiUrl , request, AIResponse.class);

        return response;
    }
}
