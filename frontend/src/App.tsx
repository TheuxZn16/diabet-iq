import { Navigate, Route, Routes } from "react-router-dom";
import PrototypeNav from "./components/PrototypeNav";
import CadastroPage from "./pages/CadastroPage";
import LoginPage from "./pages/LoginPage";
import HomePacientePage from "./pages/HomePacientePage";
import HomeMedicoPage from "./pages/HomeMedicoPage";

export default function App() {
  return (
    <div className="min-h-screen bg-canvas font-sans">
      <PrototypeNav />
      <Routes>
        <Route path="/" element={<Navigate to="/cadastro" replace />} />
        <Route path="/cadastro" element={<CadastroPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/paciente" element={<HomePacientePage />} />
        <Route path="/medico" element={<HomeMedicoPage />} />
      </Routes>
    </div>
  );
}
