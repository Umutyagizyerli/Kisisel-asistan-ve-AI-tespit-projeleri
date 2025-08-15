package com.umutyerli.kisisel_asistan.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.umutyerli.kisisel_asistan.entities.Etkinlik;
import com.umutyerli.kisisel_asistan.repository.EtkinlikRepository;

@Service
public class EtkinlikService {
    private final EtkinlikRepository etkinlikRepository;

    public EtkinlikService(EtkinlikRepository etkinlikRepository){
        this.etkinlikRepository=etkinlikRepository;
    }
    //EtkinlikService sınıfının veritabanıyla etkileşim kurmak için bir EtkinlikRepository'ye "bağımlı" olduğunu 
    //ve bu bağımlılığın kurucu metot aracılığıyla sağlandığını gösterir.

    public Etkinlik etkinlikEkle(Etkinlik etkinlik){
        return etkinlikRepository.save(etkinlik);
    }

    public List <Etkinlik> tumEtkinlikleriGetir(){
        return etkinlikRepository.findAll();
    }

    public void etkinlikSil(Long id){
        etkinlikRepository.deleteById(id);
    }

    public List <Etkinlik> bugününEtkinlikleriniGetir(){
        return etkinlikRepository.findAll()
                .stream()
                .filter(n->n.getOlusturmaTarih().equals(LocalDate.now()))
                .toList();
    }







}
