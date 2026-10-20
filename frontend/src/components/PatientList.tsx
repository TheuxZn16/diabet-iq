import type { Patient } from "../types";
import Badge from "./Badge";

const statusLabel: Record<Patient["status"], string> = {
  ok: "Estável",
  warn: "Atenção",
  high: "Alto",
};

interface PatientListProps {
  patients: Patient[];
  selectedIndex: number;
  onSelect: (index: number) => void;
}

export default function PatientList({ patients, selectedIndex, onSelect }: PatientListProps) {
  return (
    <div className="bg-white border border-hairline rounded-card overflow-hidden">
      {patients.map((p, i) => (
        <button
          key={p.name}
          onClick={() => onSelect(i)}
          className={`w-full flex items-center gap-3 px-4 py-3.5 border-b border-hairline last:border-0 text-left transition-colors border-l-[3px] ${
            i === selectedIndex
              ? "bg-primary-50 border-l-primary-600"
              : "border-l-transparent hover:bg-canvas"
          }`}
        >
          <div className="w-9 h-9 rounded-full bg-primary-100 text-primary-700 flex items-center justify-center text-[13px] font-bold shrink-0">
            {p.initials}
          </div>
          <div className="flex-1 min-w-0">
            <div className="text-sm font-bold text-ink-900 truncate">{p.name}</div>
            <div className="text-xs text-ink-400 truncate">
              {p.age} anos · Diabetes {p.type}
            </div>
            <div className="sm:hidden mt-1">
              <Badge status={p.status}>{statusLabel[p.status]}</Badge>
            </div>
          </div>
          <Badge status={p.status} className="hidden sm:inline-flex shrink-0">
            {statusLabel[p.status]}
          </Badge>
          <div className="text-right shrink-0">
            <div className="text-[15px] font-display font-extrabold text-ink-900">{p.last}</div>
            <div className="text-[11px] text-ink-400 font-medium">mg/dL</div>
          </div>
          <svg
            width="16"
            height="16"
            viewBox="0 0 24 24"
            fill="none"
            stroke="#8A97AA"
            strokeWidth="2"
            strokeLinecap="round"
            className="hidden sm:block shrink-0"
          >
            <path d="M9 6l6 6-6 6" />
          </svg>
        </button>
      ))}
    </div>
  );
}
