# API SÜRÜMÜ

from flask_cors import CORS
from flask import Flask, request, jsonify
#Flask: Basit web sunucusu framework'ü
#request: API'ye gelen HTTP isteklerini almayı sağlar
#jsonify:Python verilerini JSON formatına dönüştürür , HTTP yanıtı için
from transformers import pipeline # huggingFace transformers kütüphanesinden bazı kütüphaneleri kullanmayı sağlayan fonksiyon,
#pipeline: karmaşık model yükleme işlemlerini basitleştirir

# yeni bir flask uygulaması oluşturulur
app = Flask(__name__) # Flask'in dosyanın doğrudan mı çalıştırıldığını yoksa başka bir yerden mi çağrıldığını anlamasını sağlar
CORS(app) 

# Modeli yükle
model_pipeline = pipeline("text-classification", model="fakespot-ai/roberta-base-ai-text-detection-v1")

@app.route('/predict', methods=['POST'])#/predict URL'sine gelen POST isteklerine cevap verecek bir endpoint tanımlar, yani kullanıcı metin gönderdiğinde burasıyla birlikte aşağıdaki fonksiyon çalışır
def predict():
    data = request.get_json() # API'ye gelen JSON içeriğini(body) dic(sözlük olarak alır) {"text":"buraya metin gelecek"}
    text = data.get("text", "") # JSON verisinden "text" alanını alır, eğer text yoksa boş string ("") verir

    if not text:
        return jsonify({"error": "Metin Gerekli"}), 400

    result = model_pipeline(text)[0] # modelin döndürdüğü liste içinden ilk sonucu alır
    response={
        "result" : result["label"],
        "score" : float(result["score"])
        
    }
    
    return jsonify(response) # sonucu json olarak döner

if __name__ == '__main__': # bu dosya doğrudan çalıştıırldığında Flask suncusu başlatılır
    app.run(host="0.0.0.0", port=5001) # 5001 portundan istek atılır
