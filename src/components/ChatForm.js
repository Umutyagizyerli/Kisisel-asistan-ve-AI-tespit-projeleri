import React, {useState} from "react"; // bileşen içinde state(durum) tanımlamak için useState hook'u kullanırız

function ChatForm({onMesajGönder}){ // (App.js)parent(üst, ebeveyn) bileşenden onMesajGönder fonksiyonu prop olarak geliyor.
    // bu fonksiyon mesajı backende göndermek için kullanılacak

    // useState önemli
    const [mesaj,setMesaj] = useState(""); // mesaj:textarea içinde yazılan metni tutan state
    //setMesaj: bu state'i güncelleyen fonksiyon, 
    // useState(""): başlangıç değeri boş string
    // kullanıcı textarea'ya yazdıkça setMesaj ile bu değer güncellenir, react bu state değişimini fark edip bileşeni tekrar render ediyor

    //handleSubmit: form gönderildiğinde çalışacak fonksiyon
    const handleSubmit = (e) =>{ // buradaki e event(olay) nesnesidir, react'ta kullanıcı etkileşimi gerçekleştiğinde(butona bas, inputa yaz vb), otomatik olarak bu event nesnesi fonksiyona parametre olarak verilir
        e.preventDefault(); // tarayıcının formu sayfayı yenileyerek göndermesi engelleniyor
        if(mesaj.trim()==="") return; //kullanıcı sadece boşluk girerse bile mesaj göndermesi engelleniyor, .trim(): baştaki ve sondaki boşlukları siler
        onMesajGönder(mesaj);//üst bileşenden(parent) gelen onMesajGönder fonksiyonuna, kullanıcı mesajını parametre olarak verir
        // bu sayede parent bileşen (App.js) bu mesajı alıp backend'e gönderiyor
        setMesaj(""); // mesaj gönderildikten sonra textarea'yı boşaltıyor
    };

    return (
    <form onSubmit={handleSubmit} className="mb-4">
      <textarea
        rows={4} // satır yüksekliği
        className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
        placeholder="Mesajınızı yazın..."
        value={mesaj} // textarea'nın değeri state'e bağlı (controlled component)
        onChange={(e) => setMesaj(e.target.value)} // kullanıcı yazdıkça setMesaj çağrılır ve state güncellenir
      />
      <button 
      type="submit"
      className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded"
      >
        Gönder
      </button>
    </form>
  );
}
export default ChatForm;
// form submit edğildiğinde onMesajGönder tetiklenir ve backend'e istek atılır