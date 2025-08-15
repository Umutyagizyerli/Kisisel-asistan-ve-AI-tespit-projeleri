import React, { useState } from "react";
import ChatForm from "../components/ChatForm";
import axios from "axios";

function AiTespit() {
  const [yanit, setYanit] = useState("");

  // Backend API url'si, Flask API port ve endpointine göre ayarla
  const API_URL = "http://localhost:5001/predict";

  // Mesaj gönderme fonksiyonu, ChatForm'dan gelecek mesajı alır, API'ye gönderir, cevabı setYanit ile gösterir
  const mesajGonder = async (mesaj) => {
    try {
      const response = await axios.post(API_URL, { text: mesaj });
      setYanit(`Tahmin: ${response.data.result} (Güven: ${(response.data.score * 100).toFixed(2)}%)`);
    } catch (error) {
      console.error("API bağlantı hatası:", error);
      setYanit("API'ye bağlanırken hata oluştu.");
    }
  };

  return (
    <div className="max-w-3xl mx-auto p-8 font-['Poppins'] bg-gray-100 min-h-screen text-gray-800 text-center">
      <h1 className="text-5xl font-extrabold text-green-700 mb-6">AI Tespiti</h1>
      <ChatForm onMesajGonder={mesajGonder} />
      <div className="mt-6 bg-white p-6 rounded shadow min-h-[5rem]">
        {yanit || "Mesaj gönderip sonucu bekleyin..."}
      </div>
    </div>
  );
}

export default AiTespit;
