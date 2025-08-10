import React from "react";

function EtkinlikList({ baslik, etkinlikler }) {
  return (
    <div className="my-6 p-4 bg-white rounded-lg shadow-md">
      <h2 className="text-2xl font-semibold mb-4 text-gray-800">{baslik}</h2>
      {etkinlikler.length === 0 ? (
        <p className="text-gray-500 italic">Gösterilecek etkinlik yok.</p>
      ) : (
        <ul className="list-disc list-inside space-y-3"> {/*ul: sırasız liste, li: listenin yani ul'nin ögeleridir */}
          {etkinlikler.map((etkinlik) => (
            <li key={etkinlik.id} className="text-gray-700">
              <strong className="font-semibold">{etkinlik.baslik}</strong>: {etkinlik.aciklama} —{" "}
              <span className="text-sm text-gray-500">
                {etkinlik.etkinlikTarih} {etkinlik.etkinlikSaati}
              </span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default EtkinlikList;
