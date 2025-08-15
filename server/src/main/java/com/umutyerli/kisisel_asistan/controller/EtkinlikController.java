package com.umutyerli.kisisel_asistan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umutyerli.kisisel_asistan.entities.Etkinlik;
import com.umutyerli.kisisel_asistan.services.EtkinlikService;

@RestController
@RequestMapping("/api/etkinlikler")
@CrossOrigin
public class EtkinlikController {

    private final EtkinlikService etkinlikService;

    public EtkinlikController(EtkinlikService etkinlikService){
        this.etkinlikService=etkinlikService;
    }

    @PostMapping
    public Etkinlik etkinlikEkle(@RequestBody Etkinlik etkinlik){//@RequestBody =http istediğinin body'sindeki veriyi java nesnesine otomatik olarak dönüştürmek ve bir metot parametresine bağlamak için kullanılır, post ve put isteklerinde kullanılır
        return etkinlikService.etkinlikEkle(etkinlik);
    }

    @DeleteMapping("/{id}")
    public void etkinlikSil(@PathVariable Long id){ //@PathVariable = Id gibi değerleri URL'den almak için kullanılır çoğunlukla
        etkinlikService.etkinlikSil(id);
    }

    @GetMapping
    public List<Etkinlik> tumEtkinlikler(){
        return etkinlikService.tumEtkinlikleriGetir();
    }

    @GetMapping("/bugun")
    public List<Etkinlik> bugununEtkinlikleri(){
        return etkinlikService.bugününEtkinlikleriniGetir();
    }

    

}
