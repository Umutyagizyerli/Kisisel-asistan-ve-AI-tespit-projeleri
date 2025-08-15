package com.umutyerli.kisisel_asistan.dto;

import lombok.Data;

@Data
public class AIResponse { // modelin döndüğü veri
    private String result; // insan, insan değil
    private double score;  // modelin güven skoru
}
