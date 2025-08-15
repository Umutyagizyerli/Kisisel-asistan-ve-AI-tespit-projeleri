package com.umutyerli.kisisel_asistan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umutyerli.kisisel_asistan.entities.Not;
import com.umutyerli.kisisel_asistan.services.NotService;


@RestController // RESTful web servisi olduğunu belirtir
@RequestMapping("/api/notlar")
@CrossOrigin // farklı domainlerden gelen web isteklerinin bu uygulamaya erişmesine izin verir
public class NotController {

    private final NotService notService; // private yazarak nesnenin sadece NotService sınıfının içinden erişilebilmesini sağlar,bu değeri değiştirmek için getter setter kullanmak gerekir
    // final ile o değişkenin değeri sadece 1 kere atanabilir ve daha sonra değiştirilemez

    public NotController(NotService notService){
        this.notService=notService;
    }

    @PostMapping
    public Not notEkle(@RequestBody Not not){
        return notService.notEkle(not);
    }
    
    @GetMapping
    public List<Not> tumNotlar(){
        return notService.tumNotlarıGetir();
    }

    @GetMapping("/bugun")
    public List<Not> bugununNotlari(){
        return notService.bugununNotlariniGetir();
    }




}
