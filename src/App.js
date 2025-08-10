
import axios from "axios";
import "./App.css";

import React, { useState } from 'react';
import ChatForm from './components/ChatForm.js';
import NotList from './components/NotList.js';
import EtkinlikList from './components/EtkinlikList.js';

// useState sayesinde React bileşeninde değişkenler tutup onları değiştirebiliyoruz
function App(){ // react bileşenleri tanımlanır, uygulamanın ana componenti burası
  const [yanit, setYanit] =useState(""); // yanit state'ini güncellemek için setYanit kullanılır, useState bir hook(kanca)'dır
  const [tumNotlar, setTumNotlar] = useState([]); // tüm notlar dizisini güncellemek için 
  const [bugununNotlari,setBugununNotlari] = useState([]);
  const [tumEtkinlikler, setTumEtkinlikler]= useState([]);
  const [bugununEtkinlikleri, setBugununEtkinlikleri]= useState([]);

  // Backend API URL, localhost portunu kendi backend poruna göre ayarla
  const API_BASE = "http://localhost:8080/api";

  // mesaj gönder fonksiyonu
  // burası kullanıcıdan alınan mesajı backend API'ye gönderir API'den gelen cevabı alır ve kullanıcı arayüzünde gösterir
  const mesajGonder = async(mesaj)=> { // mesaj gönder adında asenkron fonksiyon tanımlandı,parametre olarak kullanıcıdan gelen mesaj stringini alıyor
    try{
      const response = await axios.post(`${API_BASE}/chatbot`, { mesaj });
      // axios.post ile HTTP POST isteği yapılıyor, {mesaj}--> {"mesaj":"kullanıcının yazdığı mesaj"}, await ile istek tamamlananan kadar beklenir, sonuç da response değişkeninde tutulur
      setYanit(response.data); // sunucudan gelen yanıt reponse.data ile alınıyor, setYanit ile react state'ine atanır
      // response.data ile API'nin gönderdiği asıl veri içeriği seçilir(mesela json içeriği)
    }catch(error){
      console.error("mesaj gönderme hatası", error);
      setYanit("sunucuya bağlanırken hata oluştu");
    }
  };

  // API'den verileri çekmek için ortak fonksiyon, farklı api endpointlerinden veri çekip direkt ilgili react state'ine yazabiliriz
  // endpoint: API'den hangi veriyi alacağımızı belirleyen url parçası (mesela notlar)
  // setState:React'te state güncelleme fonksiyonu (mesela setTumNotlar)
  const veriGetir = async (endpoint, setState) => {
    try { // await ile istek tamamlanana kadar bekleriz ve gelen yanıt response değişkenine atanır
      const response = await axios.get(`${API_BASE}/${endpoint}`);
      setState(response.data); // sunucudan dönen yanıt(response) içindeki data kısmın ıalıyoruz, asıl veri data kısmında bulunur
    } catch (error) {
      console.error(`${endpoint} verileri alınamadı:`, error);
    }
  };

  // ekranda gözükecem kısım
  return (
    <div className="max-w-xl mx-auto p-6 font-sans bg-gray-50 min-h-screen">
      <h1 className="text-4xl font-bold mb-8 text-center text-gray-900">Kişisel Asistan Chatbot</h1>

      <ChatForm onMesajGonder={mesajGonder} />

      <h2 className="text-2xl font-semibold mt-10 mb-4 text-gray-800">Cevap:</h2>
      <p className="whitespace-pre-wrap bg-white p-4 rounded-md shadow-inner border border-gray-300 text-gray-700 min-h-[4rem]">{yanit}</p>

      <hr className="my-8 border-gray-300" />

      <div>
        <div className="mb-4 flex flex-wrap gap-3">
          <button 
            onClick={() => veriGetir("notlar", setTumNotlar)} 
            className="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700 transition"
          >
            Tüm Notları Getir
          </button>
          <button 
            onClick={() => veriGetir("notlar/bugun", setBugununNotlari)} 
            className="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700 transition"
          >
            Bugünün Notlarını Getir
          </button>
        </div>
        <NotList baslik="Tüm Notlar" notlar={tumNotlar} />
        <NotList baslik="Bugünün Notları" notlar={bugununNotlari} />
      </div>

      <hr className="my-8 border-gray-300" />

      <div>
        <div className="mb-4 flex flex-wrap gap-3">
          <button 
            onClick={() => veriGetir("etkinlikler", setTumEtkinlikler)} 
            className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 transition"
          >
            Tüm Etkinlikleri Getir
          </button>
          <button 
            onClick={() => veriGetir("etkinlikler/bugun", setBugununEtkinlikleri)} 
            className="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 transition"
          >
            Bugünün Etkinliklerini Getir
          </button>
        </div>
        <EtkinlikList baslik="Tüm Etkinlikler" etkinlikler={tumEtkinlikler} />
        <EtkinlikList baslik="Bugünün Etkinlikleri" etkinlikler={bugununEtkinlikleri} />
      </div>
    </div>
  );
}

export default App;




