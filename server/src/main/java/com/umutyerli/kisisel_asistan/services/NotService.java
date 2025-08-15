package com.umutyerli.kisisel_asistan.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.umutyerli.kisisel_asistan.entities.Not;
import com.umutyerli.kisisel_asistan.repository.NotRepository;

@Service // servis anatasyonu olduğunu belirtmek için
public class NotService { // not ekleme, listeleme gibi işlemleri gerçekleştirir, REPOSİTORY ile CONTROLLER arasında bir köprü görevi görür 
    private final NotRepository notRepository; // notRepository veritabanı işlemlerini yapmak için kullanılan Repository nesnesini tutar, final olması bu alanın yalnızca constructor'da başlatılabileceğini ve sonradan değiştirilemeyeceğini garantiler

    // constructor bir sınıfın nesnesini oluştururken otomatik olarak çağrılan bir metottur, sınıf adıyla aynı isme sahip ve geri dönüş türü yoktur
    public NotService(NotRepository notRepository){
        this.notRepository=notRepository;// constructordan gelen notRepository parametresini bu sınıfın notRepository alanına atar
    }

    public Not notEkle(Not not) { // save metodu Spring Data JPA'nın JpaRepository arayüzünden gelir ve Not'u notes tablosuna kaydeder
        return notRepository.save(not); // gelen not listesini veritabanına kaydeder
    }

    public List <Not> tumNotlarıGetir() { // tüm notları içeren bir List<Not> döndürür
        return notRepository.findAll(); // findAll ile veritbanındaki tüm not kayıtları getirilir, findAll da JpaRepository'nin hazır bir modülüdür
    }

    public List <Not> bugununNotlariniGetir(){// bugüne ait notları List<Not> türünde döndüren metot
        return notRepository.findAll() // findAll ile tüm notları çektik 
                .stream() // findAll'ın döndürdüğü List<Not>'u bir Java Stream'e dönüştürür, Stream API liste üzerinde filtreleme,dönüştürme gibi işlemler yapmayı sağlar biz burda filtreleme için kullanacağız
                .filter(n -> n.getTarih().equals(LocalDate.now()))// stream'deki her not listesini n ile kontrol ediyoruz ve koşulu sağlayanları tutarız,
                .toList(); // filtrelenmiş stream'i List<Not> 'a dönüştürür
    } // mesela not.java'daki tarih yerine xxx yazsaydık burada getXxx yazmamız gerekirdi

    

}
