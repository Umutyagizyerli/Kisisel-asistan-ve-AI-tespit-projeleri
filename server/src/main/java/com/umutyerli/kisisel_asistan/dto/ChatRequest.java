package com.umutyerli.kisisel_asistan.dto;

import lombok.Data;

@Data // bunun sayesinde buraya getter setter metotlarını ayrı ayrı yazmaya gerek yok bu işlem otomatik yapılıyor
public class ChatRequest { //Kullanıcının doğal dilde yazdığı mesajlar burada temsil edilir
    private String mesaj;
}
