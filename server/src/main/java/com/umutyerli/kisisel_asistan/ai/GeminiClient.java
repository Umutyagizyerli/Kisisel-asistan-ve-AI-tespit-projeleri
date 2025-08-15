package com.umutyerli.kisisel_asistan.ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeminiClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

    public Map<String, String> yorumdanVeriCek(String mesaj) {
        RestTemplate restTemplate = new RestTemplate();

        String prompt = "Aşağıdaki mesajı analiz et ve şu formatta dön: \n" +
                        "ETKINLIK|Başlık|Açıklama|Tarih(yyyy-MM-dd)|Saat(HH:mm)\n" +
                        "veya\n" +
                        "NOT|Başlık|Açıklama|Tarih(yyyy-MM-dd)\n\n" +
                        "Mesaj: " + mesaj;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(
            Map.of("parts", List.of(Map.of("text", prompt)))
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
            GEMINI_API_URL + "?key=" + apiKey,
            request,
            Map.class
        );

        try {
            Map<String, Object> candidate = ((List<Map<String, Object>>) response.getBody().get("candidates")).get(0);
            Map<String, Object> content = (Map<String, Object>) candidate.get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            String cevap = (String) parts.get(0).get("text");

            // Cevabı parçala
            String[] parcalar = cevap.split("\\|");

            Map<String, String> sonuc = new HashMap<>();

            if (parcalar[0].trim().equalsIgnoreCase("ETKINLIK") && parcalar.length == 5) {
                sonuc.put("tur", "etkinlik");
                sonuc.put("baslik", parcalar[1].trim());
                sonuc.put("aciklama", parcalar[2].trim());
                sonuc.put("tarih", parcalar[3].trim());
                sonuc.put("saat", parcalar[4].trim());
            } else if (parcalar[0].trim().equalsIgnoreCase("NOT") && parcalar.length == 4) {
                sonuc.put("tur", "not");
                sonuc.put("baslik", parcalar[1].trim());
                sonuc.put("icerik", parcalar[2].trim());
                sonuc.put("tarih", parcalar[3].trim());
            } else {
                sonuc.put("tur", "bilinmiyor");
                sonuc.put("hamCevap", cevap);
            }

            return sonuc;

        } catch (Exception e) {
            Map<String, String> hata = new HashMap<>();
            hata.put("tur", "hata");
            hata.put("mesaj", "Gemini cevabı alınamadı veya işlenemedi: " + e.getMessage());
            return hata;
        }
    }
}
