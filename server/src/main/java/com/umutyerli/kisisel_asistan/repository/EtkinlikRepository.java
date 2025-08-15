package com.umutyerli.kisisel_asistan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umutyerli.kisisel_asistan.entities.Etkinlik;

public interface EtkinlikRepository extends JpaRepository <Etkinlik,Long > {
    List<Etkinlik> findByEtkinlikTarih(LocalDate tarih);
}
