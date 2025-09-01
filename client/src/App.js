import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import KisiselAsistan from "./pages/KisiselAsistan";
import AiTespit from "./pages/AiTespiti";

function App() {
  return (
    <Router>
      <nav className="bg-white shadow-md sticky top-0 z-50 ">
        <div className="max-w-5xl mx-auto px-8 py-4 flex justify-between items-center">
          <Link
            to="/"
            className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded"
          >
            Kişisel Asistan
          </Link>
          <Link
            to="/ai-tespit"
            className="px-4 py-2 bg-green-500 text-white rounded-lg hover:bg-green-600 transition"
          >
            AI Tespiti
          </Link>
        </div>
      </nav>

      <Routes>
        <Route path="/" element={<KisiselAsistan />} />
        <Route path="/ai-tespit" element={<AiTespit />} />
      </Routes>
    </Router>
  );
}

export default App;
