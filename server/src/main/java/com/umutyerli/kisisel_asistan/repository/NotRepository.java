package com.umutyerli.kisisel_asistan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; // spring data jpa kütüphanesinden JpaRepository arayüzünü içeri aktarır

import com.umutyerli.kisisel_asistan.entities.Not; // bu adres paketinde bulunan Not adlı java sınıfını içeri aktarır

//JpaRepository veritabanı işlemlerini(CRUD=Create-Read-Update-Delete) işlemlerini yapmak için kullanılır

// Not Entity'si için veritabanı işlemlerini gerçekleştirmek için yazılır
public interface NotRepository extends JpaRepository <Not , Long>{ // <hangi entity kullanılacak , birincil anahtar türü(yani ıd türü , id'ye long demiştik veritabanında)>
    List<Not> findByTarih(LocalDate tarih);
}
// not entitiysi ile veritabanı işlemleri yapmak için kullanılır