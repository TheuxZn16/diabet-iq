import { useState } from "react";
import Sidebar from "../components/Sidebar";
import TopBar from "../components/TopBar";
import MetricCard from "../components/MetricCard";
import Badge from "../components/Badge";
import PatientList from "../components/PatientList";
import PatientDetail from "../components/PatientDetail";
import { patients } from "../data/mockData";

export default function HomeMedicoPage() {
  const [selectedIndex, setSelectedIndex] = useState(0);
  const [menuOpen, setMenuOpen] = useState(false);
  const selectedPatient = patients[selectedIndex];
  const alertCount = patients.filter((p) => p.status === "high").length;

  return (
    <div className="flex min-h-[calc(100vh-49px)]">
      <Sidebar
        role="medico"
        userName="Dr. João Pereira"
        userSubtitle="Endocrinologia"
        initials="JP"
        open={menuOpen}
        onClose={() => setMenuOpen(false)}
      />

      <main className="flex-1 min-w-0 px-4 sm:px-6 md:px-8 py-5 md:py-7 pb-12">
        <TopBar
          title="Seus pacientes"
          subtitle={`${patients.length} pacientes vinculados · ${alertCount} precisam de atenção hoje`}
          initials="JP"
          onMenuClick={() => setMenuOpen(true)}
        />

        <div className="grid grid-cols-1 sm:grid-cols-3 gap-3 sm:gap-3.5 mb-5">
          <MetricCard label="Total de pacientes" value={patients.length} />
          <MetricCard
            label="Em alerta"
            value={alertCount}
            valueClassName="text-danger-600"
            badge={<Badge status="high" className="mt-2">Fora da meta</Badge>}
          />
          <MetricCard label="Registros hoje" value={58} />
        </div>

        <div className="flex gap-2.5 mb-5 flex-wrap">
          <div className="flex-1 min-w-[220px] flex items-center gap-2 bg-white border border-ink-200 rounded-ctrl px-3.5 py-2.5">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" className="text-ink-400 shrink-0">
              <circle cx="11" cy="11" r="7" />
              <path d="M21 21l-4-4" />
            </svg>
            <input
              type="text"
              placeholder="Buscar paciente por nome"
              className="flex-1 outline-none text-sm text-ink-900 placeholder:text-ink-400 min-w-0"
            />
          </div>
          <select className="w-full sm:w-auto border border-ink-200 rounded-ctrl px-3.5 py-2.5 text-sm text-ink-700 bg-white">
            <option>Todos os pacientes</option>
            <option>Em alerta</option>
            <option>Estáveis</option>
          </select>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-[1.15fr_1fr] gap-4 items-start">
          <PatientList patients={patients} selectedIndex={selectedIndex} onSelect={setSelectedIndex} />
          <PatientDetail patient={selectedPatient} />
        </div>
      </main>
    </div>
  );
}
