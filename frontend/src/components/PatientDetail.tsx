import type { Patient } from "../types";
import LineChart from "./charts/LineChart";
import { SecondaryButton } from "./Buttons";

export default function PatientDetail({ patient }: { patient: Patient }) {
  return (
    <div className="bg-white border border-hairline rounded-card p-5">
      <div className="flex items-center gap-3 mb-4">
        <div className="w-12 h-12 rounded-full bg-primary-100 text-primary-700 flex items-center justify-center text-[15px] font-bold shrink-0">
          {patient.initials}
        </div>
        <div>
          <div className="text-[17px] font-display font-extrabold text-ink-900">{patient.name}</div>
          <div className="text-[13px] text-ink-600">
            {patient.age} anos · Diabetes {patient.type}
          </div>
        </div>
      </div>

      <div className="grid grid-cols-2 gap-2.5 mb-4">
        <div className="bg-canvas rounded-xl px-3.5 py-3">
          <div className="text-[11px] font-semibold text-ink-600 mb-1">Última medição</div>
          <div className="text-lg font-display font-extrabold text-ink-900">{patient.last} mg/dL</div>
        </div>
        <div className="bg-canvas rounded-xl px-3.5 py-3">
          <div className="text-[11px] font-semibold text-ink-600 mb-1">Média · 7 dias</div>
          <div className="text-lg font-display font-extrabold text-ink-900">{patient.avg} mg/dL</div>
        </div>
        <div className="bg-canvas rounded-xl px-3.5 py-3">
          <div className="text-[11px] font-semibold text-ink-600 mb-1">Tempo no alvo</div>
          <div className="text-lg font-display font-extrabold text-ink-900">{patient.target}%</div>
        </div>
        <div className="bg-canvas rounded-xl px-3.5 py-3">
          <div className="text-[11px] font-semibold text-ink-600 mb-1">Tendência</div>
          <div className="text-lg font-display font-extrabold text-ink-900">{patient.trend} mg/dL</div>
        </div>
      </div>

      <h3 className="text-[13px] font-bold text-ink-900 mb-2">Últimos 7 dias</h3>
      <LineChart values={patient.series} targetMin={80} targetMax={140} width={300} height={140} />

      <SecondaryButton className="mt-4">Ver histórico completo</SecondaryButton>
    </div>
  );
}
