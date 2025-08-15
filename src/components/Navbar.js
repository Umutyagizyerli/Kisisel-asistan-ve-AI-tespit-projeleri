import React from "react";
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="bg-white shadow-md sticky top-0 z-50">
      <div className="max-w-5xl mx-auto px-8 py-4 flex justify-between items-center">
        <Link
          to="/"
          className="text-2xl font-bold text-blue-800 hover:text-blue-600 transition"
        >
          Kişisel Asistan
        </Link>
        <Link
          to="/ai-tespiti"
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition"
        >
          AI Tespiti
        </Link>
      </div>
    </nav>
  );
}
