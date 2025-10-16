# Personal Assistant Chatbot

Bu proje, kullanıcıların doğal dilde komutlar vererek not ve etkinlik yönetimi yapabildiği, ayrıca genel sohbetler gerçekleştirebildiği bir kişisel asistan uygulamasıdır.
Uygulama, Spring Boot (Java) ile geliştirilmiş bir backend, React tabanlı bir frontend ve PostgreSQL veritabanı kullanılarak oluşturulmuştur.
Yapay zekâ bileşeni olarak Gemini API entegre edilmiştir.

## Özellikler

Doğal dilde komut anlama: Kullanıcıdan alınan ifadeyi Gemini API ile analiz eder.

- Akıllı görev sınıflandırma:

- Girilen komutun not mu yoksa etkinlik mi olduğunu otomatik belirler.

- Tarih veya saat bilgisi içeren girdiler etkinlik olarak değerlendirilir.

- Diğer ifadeler not olarak kaydedilir.

## Veritabanı kaydı:

Etkinlikler için tarih, saat ve görev sütunları ayrı ayrı kaydedilir.

Notlar yalnızca içerik olarak saklanır.

Veri yönetimi: Not ve etkinliklerin listelenmesi, silinmesi ve güncellenmesi yapılabilir.

## Akıllı Görev Anlama ve Kayıt Örneği

Chatbot, girilen komutu analiz ederek türünü belirler:

"Yarın saat 15:00'te proje toplantısı var."

### Bu komut etkinlik olarak algılanır ve şu şekilde veritabanına kaydedilir:

Tür - 	  Görev	-   Tarih	 -   Saat

Etkinlik-Proje toplantısı-2025-10-17-15:00

"Veritabanı bağlantısını kontrol etmem gerekiyor."
-->> Bu ifade not olarak kaydedilir.

## Proje Yapısı (Backend)
controller/

    ChatbotController.java
service/

    ChatbotService.java
    EtkinlikService.java
    NotService.java
dto/

    ChatRequest.java
entity/

    Not.java
    Etkinlik.java
repository/

    NotRepository.java
    EtkinlikRepository.java
ai/

    GeminiClient.java

## Kurulum

### Repository’i klonlayın:

git clone <https://github.com/Umutyagizyerli/Kisisel-asistan-ve-AI-tespit-projeleri.git>


### PostgreSQL veritabanını oluşturun ve application.properties dosyasında bağlantıyı ayarlayın:

spring.datasource.url=jdbc:postgresql://localhost:5432/assistant_db
spring.datasource.username=<kullanici>
spring.datasource.password=<sifre>


### Backend’i başlatın:

./mvnw spring-boot:run


### Frontend’i çalıştırın:

cd frontend
npm install
npm start

