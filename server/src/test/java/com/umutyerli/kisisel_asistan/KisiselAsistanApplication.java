package com.umutyerli.kisisel_asistan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//Eğer bu anotasyonu eklemezsen, Spring Boot @Entity ile işaretlenmiş sınıfları (örneğin Student sınıfını) bulamaz ve hata alırsın!
@EntityScan(basePackages = {"com.umutyerli"}) //! Spring Boot'un bu sınıfı tarayabilmesi için ekledik , entityleri görsün diye ekledik
@ComponentScan(basePackages={"com.umutyerli"}) //!belirtilen paketteki bileşenleri (Component) otomatik olarak tarayıp yüklemek için kullanılır. BUNU MUTLAKA YAZMAK ZORUNDASIN, control service gibi anatasyonların tanınması için yazdık
@EnableJpaRepositories(basePackages = {"com.umutyerli"}) //!JPA Repository'lerini etkinleştirmek için kullanılan anatasyon

@SpringBootApplication // başlangıç satırı, uygulamanın çalışmasını sağlar, projenin main metodu bu 
public class KisiselAsistanApplication {
    public static void main(String[] args) {
        SpringApplication.run(KisiselAsistanApplication.class, args); // springapplication sınıfının run metodunu çağırır
    }
}