import React from "react";

function NotList({ baslik, notlar }) {
  return (
    <div className="my-6 p-4 bg-white rounded-lg shadow-md">
      <h2 className="text-2xl font-semibold mb-4 text-gray-800">{baslik}</h2>
      {notlar.length === 0 ? (
        <p className="text-gray-500 italic">Gösterilecek not yok.</p>
      ) : (
        <ul className="list-disc list-inside space-y-2">
          {notlar.map((not) => (
            <li key={not.id} className="text-gray-700">
              <strong className="font-semibold">{not.baslik}</strong>: {not.icerik}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default NotList;