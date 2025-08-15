package com.umutyerli.kisisel_asistan.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umutyerli.kisisel_asistan.ai.GeminiClient;
import com.umutyerli.kisisel_asistan.entities.Etkinlik;
import com.umutyerli.kisisel_asistan.entities.Not;
import com.umutyerli.kisisel_asistan.repository.EtkinlikRepository;
import com.umutyerli.kisisel_asistan.repository.NotRepository;


@Service
public class ChatbotService {
    
    @Autowired
    private final GeminiClient geminiClient; // Gemini API'den yanıt alır GeminiClient ile

    @Autowired
    private final NotRepository notRepository;

    @Autowired
    private final EtkinlikRepository etkinlikRepository;

    
    public ChatbotService (GeminiClient geminiClient, NotRepository notRepository, EtkinlikRepository etkinlikRepository){
        this.geminiClient = geminiClient;
        this.notRepository = notRepository;
        this.etkinlikRepository = etkinlikRepository;
    }

    private boolean gecerliTarih(String tarihStr) {
    return tarihStr != null && 
    !tarihStr.isEmpty() && 
    !tarihStr.equalsIgnoreCase("yok")&&
    !tarihStr.equalsIgnoreCase("belirtilmemiş");
    }

    private boolean gecerliSaat(String saatStr) {
    return saatStr != null && !saatStr.isEmpty() && 
    !saatStr.equalsIgnoreCase("yok")&&
    !saatStr.equalsIgnoreCase("belirtilmemiş");
    }


    public String sohbetEt(String mesaj){
        // gemini'den yanıtı al
        Map<String,String> veri = geminiClient.yorumdanVeriCek(mesaj);

        String tur = veri.get("tur");

        if(tur==null){
            return "yanıt türü anlaşılamadı , not ya da etkinlik içermiyor olabilir";
        }

        if(tur.equals("not")){

            Not not = new Not();
            not.setBaslik(veri.get("baslik"));
            not.setIcerik(veri.get("icerik"));
            //not.setTarih(LocalDate.parse(veri.get("tarih")));

            String tarihStr = veri.get("tarih");
            if(gecerliTarih(tarihStr)){
                try {
                    not.setTarih(LocalDate.parse(tarihStr));
                } catch(Exception e){
                    return "Not tarihi formatı hatalı: " + tarihStr;
                }
            }// tarih geçerli dğeilse null kalır ve set edilmez

            notRepository.save(not);
            return "Not başarıyla kaydedildi: " + not.getIcerik();

        }
        else if (tur.equals("etkinlik")){
            Etkinlik etkinlik = new Etkinlik();
            etkinlik.setBaslik(veri.get("baslik"));
            etkinlik.setAciklama(veri.get("aciklama"));
            
            String tarihStr = veri.get("tarih");
            if(gecerliTarih(tarihStr)){
                try {
                    etkinlik.setEtkinlikTarih(LocalDate.parse(tarihStr));
                } catch (Exception e) {
                    return "Etkinlik tarihi formatı hatalı " + tarihStr;
                }
            }

            String saatStr = veri.get("saat");
            if(gecerliSaat(saatStr)){
                try {
                etkinlik.setEtkinlikSaati(LocalTime.parse(saatStr));
                } catch(Exception e){
                    return "Etkinlik saati formatı hatalı: " + saatStr;
                }
            }

            etkinlikRepository.save(etkinlik);

            return "Etkinlik başarıyla kaydedildi: " + etkinlik.getBaslik() + 
                   " (" + etkinlik.getEtkinlikTarih() + " , " + etkinlik.getEtkinlikSaati() + ")";        }
            else{
                return "bilinmeyen veri türü" + tur;
            }



    }



}
