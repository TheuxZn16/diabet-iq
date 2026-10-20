import { useState } from "react";
import Sidebar from "../components/Sidebar";
import TopBar from "../components/TopBar";
import MetricCard from "../components/MetricCard";
import Badge from "../components/Badge";
import LineChart from "../components/charts/LineChart";
import BarChart from "../components/charts/BarChart";
import RecordsTable from "../components/RecordsTable";
import {
  patientGlicemiaSeries,
  weekDayLabels,
  periodoLabels,
  periodoValues,
  recentRecords,
} from "../data/mockData";

export default function HomePacientePage() {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <div className="flex min-h-[calc(100vh-49px)]">
      <Sidebar
        role="paciente"
        userName="Marina Souza"
        userSubtitle="Paciente"
        initials="MS"
        open={menuOpen}
        onClose={() => setMenuOpen(false)}
      />

      <main className="flex-1 min-w-0 px-4 sm:px-6 md:px-8 py-5 md:py-7 pb-12">
        <TopBar
          title="Olá, Marina"
          subtitle="Quinta-feira, 12 de setembro — como está sua glicemia hoje?"
          initials="MS"
          onMenuClick={() => setMenuOpen(true)}
        />

        <div className="bg-gradient-to-br from-primary-600 to-primary-700 rounded-card px-5 sm:px-6 py-5 flex items-center justify-between gap-4 flex-wrap mb-5">
          <div>
            <h2 className="text-white text-lg font-display font-bold mb-1">
              Registrar uma nova medição
            </h2>
            <p className="text-primary-100 text-sm">
              Leva 10 segundos e ajuda seu médico a acompanhar seu controle.
            </p>
          </div>
          <button className="w-full sm:w-auto bg-white text-primary-700 font-bold text-sm px-5 py-3 rounded-[11px] flex items-center justify-center gap-2 whitespace-nowrap">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.4" strokeLinecap="round">
              <path d="M12 5v14M5 12h14" />
            </svg>
            Registrar glicemia
          </button>
        </div>

        <div className="grid grid-cols-2 lg:grid-cols-4 gap-3 sm:gap-3.5 mb-5">
          <MetricCard
            label="Última medição"
            value={118}
            unit="mg/dL"
            badge={<Badge status="ok" className="mt-2">Dentro da meta</Badge>}
          />
          <MetricCard
            label="Média · 7 dias"
            value={132}
            unit="mg/dL"
            badge={<Badge status="warn" className="mt-2">Levemente acima</Badge>}
          />
          <MetricCard
            label="Tempo no alvo"
            value={76}
            unit="%"
            badge={<Badge status="ok" className="mt-2">Meta: 70%+</Badge>}
          />
          <MetricCard
            label="Registros esta semana"
            value={21}
            badge={<Badge status="ok" className="mt-2">3 por dia, em média</Badge>}
          />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-[1.5fr_1fr] gap-4 mb-4">
          <div className="bg-white border border-hairline rounded-card p-4 sm:p-5">
            <div className="flex items-center justify-between mb-4 flex-wrap gap-2">
              <h3 className="text-[15px] font-bold text-ink-900">Glicemia — últimos 7 dias</h3>
              <div className="flex gap-3.5 text-xs text-ink-600">
                <span className="flex items-center gap-1.5">
                  <span className="w-2.5 h-2.5 rounded-[3px] bg-primary-600" /> Medições
                </span>
                <span className="flex items-center gap-1.5">
                  <span className="w-2.5 h-2.5 rounded-[3px] bg-primary-100" /> Faixa alvo
                </span>
              </div>
            </div>
            <LineChart values={patientGlicemiaSeries} targetMin={80} targetMax={140} labels={weekDayLabels} />
          </div>

          <div className="bg-white border border-hairline rounded-card p-4 sm:p-5">
            <h3 className="text-[15px] font-bold text-ink-900 mb-4">Médico responsável</h3>
            <div className="flex items-center gap-3 mb-4">
              <div className="w-11 h-11 rounded-full bg-primary-100 text-primary-700 flex items-center justify-center text-sm font-bold shrink-0">
                DR
              </div>
              <div className="min-w-0">
                <div className="text-sm font-bold text-ink-900 truncate">Dr. João Pereira</div>
                <div className="text-xs text-ink-600 truncate">Endocrinologia · CRM/SP 123456</div>
              </div>
            </div>
            <div className="bg-primary-50 border border-primary-100 rounded-xl px-3.5 py-3 text-[13px] text-ink-700 flex items-center gap-2.5">
              <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" className="shrink-0">
                <rect x="3" y="5" width="18" height="16" rx="2" />
                <path d="M8 3v4M16 3v4M3 10h18" />
              </svg>
              <span>
                Próxima consulta: <b className="text-primary-700">25 de setembro, 14h30</b>
              </span>
            </div>
          </div>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-[1.5fr_1fr] gap-4">
          <div className="bg-white border border-hairline rounded-card p-4 sm:p-5 overflow-x-auto">
            <div className="flex items-center justify-between mb-4">
              <h3 className="text-[15px] font-bold text-ink-900">Registros recentes</h3>
              <a href="#" className="text-[13px] font-semibold text-primary-600">
                Ver todos
              </a>
            </div>
            <div className="min-w-[420px]">
              <RecordsTable records={recentRecords} />
            </div>
          </div>

          <div className="bg-white border border-hairline rounded-card p-4 sm:p-5">
            <h3 className="text-[15px] font-bold text-ink-900 mb-4">Por período do dia</h3>
            <BarChart labels={periodoLabels} values={periodoValues} width={260} height={200} />
          </div>
        </div>
      </main>
    </div>
  );
}
