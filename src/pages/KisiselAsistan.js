import React, { useState } from "react";
import axios from "axios";

import ChatForm from "../components/ChatForm";
import NotList from "../components/NotList";
import EtkinlikList from "../components/EtkinlikList";

function KisiselAsistan() {
  const [yanit, setYanit] = useState("");
  const [tumNotlar, setTumNotlar] = useState([]);
  const [bugununNotlari, setBugununNotlari] = useState([]);
  const [tumEtkinlikler, setTumEtkinlikler] = useState([]);
  const [bugununEtkinlikleri, setBugununEtkinlikleri] = useState([]);

  const API_BASE = "http://localhost:8080/api";

  const mesajGonder = async (mesaj) => {
    try {
      const response = await axios.post(`${API_BASE}/chatbot`, { mesaj });
      setYanit(response.data);
    } catch (error) {
      console.error("mesaj gönderme hatası", error);
      setYanit("sunucuya bağlanırken hata oluştu");
    }
  };

  const veriGetir = async (endpoint, setState) => {
    try {
      const response = await axios.get(`${API_BASE}/${endpoint}`);
      setState(response.data);
    } catch (error) {
      console.error(`${endpoint} verileri alınamadı:`, error);
    }
  };

  return (
    <div className="max-w-3xl mx-auto p-8 font-['Poppins'] bg-gray-100 min-h-screen text-gray-800">
      <div className="text-center mb-12">
        <h1 className="text-5xl font-extrabold text-blue-800 tracking-tight">
          Kişisel Asistan
        </h1>
      </div>

      <div className="bg-white p-8 rounded-2xl shadow-xl border border-gray-200">
        <ChatForm onMesajGonder={mesajGonder} />

        <div className="mt-8">
          <h2 className="text-2xl font-bold mb-4 text-gray-900">Cevap:</h2>
          <p className="whitespace-pre-wrap bg-gray-50 p-6 rounded-xl shadow-inner border border-gray-100 text-gray-700 min-h-[5rem] transition-colors duration-200">
            {yanit}
          </p>
        </div>
      </div>

      <div className="my-10 border-t-2 border-gray-200"></div>

      <div className="grid md:grid-cols-2 gap-8">
        <div className="bg-white p-8 rounded-2xl shadow-xl border border-gray-200">
          <h3 className="text-xl font-bold text-gray-900 mb-4">Notlar</h3>
          <div className="flex flex-col gap-4 mb-6">
            <button
              onClick={() => veriGetir("notlar", setTumNotlar)}
              className="flex-1 px-6 py-3 bg-indigo-600 text-white rounded-full font-semibold shadow-lg hover:bg-indigo-700 transition transform hover:scale-105 active:scale-95"
            >
              Tüm Notları Getir
            </button>
            <button
              onClick={() => veriGetir("notlar/bugun", setBugununNotlari)}
              className="flex-1 px-6 py-3 bg-white text-indigo-600 rounded-full font-semibold border-2 border-indigo-600 hover:bg-indigo-50 transition transform hover:scale-105 active:scale-95"
            >
              Bugünün Notlarını Getir
            </button>
          </div>
          <NotList baslik="Tüm Notlar" notlar={tumNotlar} />
          <NotList baslik="Bugünün Notları" notlar={bugununNotlari} />
        </div>

        <div className="bg-white p-8 rounded-2xl shadow-xl border border-gray-200">
          <h3 className="text-xl font-bold text-gray-900 mb-4">Etkinlikler</h3>
          <div className="flex flex-col gap-4 mb-6">
            <button
              onClick={() => veriGetir("etkinlikler", setTumEtkinlikler)}
              className="flex-1 px-6 py-3 bg-green-600 text-white rounded-full font-semibold shadow-lg hover:bg-green-700 transition transform hover:scale-105 active:scale-95"
            >
              Tüm Etkinlikleri Getir
            </button>
            <button
              onClick={() => veriGetir("etkinlikler/bugun", setBugununEtkinlikleri)}
              className="flex-1 px-6 py-3 bg-white text-green-600 rounded-full font-semibold border-2 border-green-600 hover:bg-green-50 transition transform hover:scale-105 active:scale-95"
            >
              Bugünün Etkinliklerini Getir
            </button>
          </div>
          <EtkinlikList baslik="Tüm Etkinlikler" etkinlikler={tumEtkinlikler} />
          <EtkinlikList baslik="Bugünün Etkinlikleri" etkinlikler={bugununEtkinlikleri} />
        </div>
      </div>
    </div>
  );
}

export default KisiselAsistan;
