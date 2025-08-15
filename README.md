# Kişisel Asistan & AI Tespit Projesi

Bu proje, **React ve Spring Boot** kullanılarak geliştirilmiş bir web uygulamasıdır. İki ana özelliği vardır:

1. **Kişisel Asistan**: Kullanıcıların not ve etkinliklerini kaydedebileceği, listeleyebileceği ve yönetebileceği bir bölüm.
2. **AI Tespit**: Girilen bir metnin **AI tarafından mı üretildiği yoksa insan tarafından mı yazıldığı** bilgisini tespit eden bir yapay zeka modeli içerir.

---

## 🚀 Teknolojiler

- **Frontend**: React, Tailwind CSS
- **Backend**: Spring Boot, Java 23
- **Veritabanı**: PostgreSQL
- **AI Modeli**: HuggingFace Transformers (`fakespot-ai/roberta-base-ai-text-detection-v1`)
- **API**: Flask (AI tespit servisi)

---

## ⚙️ Özellikler

### Kişisel Asistan

- Kullanıcılar not ve etkinlik ekleyebilir, listeleyebilir ve silebilir.
- Veriler PostgreSQL veritabanında saklanır.
- Basit ve kullanıcı dostu arayüz ile hızlı erişim.

### AI Tespit

- Kullanıcı tarafından girilen metin, Flask tabanlı bir API’ye gönderilir.
- Model, metnin AI mı insan mı ürettiğini tahmin eder ve doğruluk yüzdesi ile döner.
- React üzerinden kullanıcı arayüzü ile hızlı sonuç gösterimi.

---

## 💻 Kurulum

1. **Backend** (Spring Boot) için:
   ```bash
   cd server
   mvn clean install
   mvn spring-boot:run
   ```
2. **Frontend** (React) için:
   ```bash
   cd client
   npm install
   npm start
   ```
3. **AI Tespit API** (Flask) için:
   ```bash
   cd ai_tespit
   pip install -r requirements.txt
   python ai_detector_api.py
   ```

---

---

## 🔮 Gelecekte Eklenebilecek Özellikler

- **Kişisel Asistan**:

  - Kullanıcı yetkilendirme ve oturum yönetimi
  - Takvim entegrasyonu ve hatırlatıcılar
  - Notlara dosya veya resim ekleme
  - Mobil uyumlu ve offline kullanım

- **AI Tespit**:

  - Farklı AI modelleri ile doğruluk karşılaştırması
  - Metin karakteristiklerine göre detaylı analiz ve skor
  - Çok dilli destek
  - AI üretimi metinleri görselleştiren interaktif grafikler

- **Genel**:
  - Kullanıcı arayüzünde Dark Mode
  - Uygulama performans iyileştirmeleri
  - Docker ile tam konteynerleşmiş deployment

---
