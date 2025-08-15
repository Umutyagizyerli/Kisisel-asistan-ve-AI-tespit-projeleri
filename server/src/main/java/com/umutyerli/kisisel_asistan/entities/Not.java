package com.umutyerli.kisisel_asistan.entities;

import java.time.LocalDate;  

import com.fasterxml.jackson.annotation.JsonFormat; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity // Java sınıfının veritabanında bir tabloya karşılık geldiğini belirtir, yani bu sınıf bir varlık olarak işaretlenir, Not sınıfı veritabanına bir tabloya karşılık gelir,sınıfın içindeki alanlar mesela id,title... tablodaki sütunlara eşlenir
@Data // Lombok'a bu sınıf için gerekli metotları üretmesini söyler
@Table(name="notes")
public class Not { // enttiy ve data anatosyonları ile sınıfın veritabano tablosuna eşlenecek bir model olduğunu ve otomatik olarak bazı metotların üretileceğini belirtir
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id alanının otomatik artan olduğunu gösterir
    private Long id;

    @Column(name="title", nullable=false)
    private String baslik;

    @Column(name="content", nullable=true)
    private String icerik;

    //@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="created_at", nullable=true)
    private LocalDate tarih;

}
