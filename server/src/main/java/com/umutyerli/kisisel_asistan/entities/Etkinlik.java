package com.umutyerli.kisisel_asistan.entities;

import java.time.LocalDate;  
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // @data getter ve setterlerı içeriyor ama biz yine de yazdık  

@Entity
@Data
@Table(name="events")
public class Etkinlik {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false) // buradaki name veritabanı ile aynı olmalı
    private String baslik;   // buradaki String ismi postmanle aynı olmalı

    @Column(name="description", nullable=true) // nullable'ın default değeri zaten true , bunu yazmasak da olur
    private String aciklama;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="event_date",nullable=true)
    private LocalDate etkinlikTarih;

    @Column(name="event_time",nullable=true)
    private LocalTime etkinlikSaati;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="created_at",nullable=false, updatable=false) // updatable=false ile created_at 1 kez oluşturulur ve değiştirilemez
    private LocalDate olusturmaTarih;

    @PrePersist //burası, entity veritabanına kaydedilmeden önce çalışır,yani kayıt veritabanına eklendiği an otomatik olarak belirlenir
    protected void onCreate(){
        this.olusturmaTarih=LocalDate.now();
    }


}
