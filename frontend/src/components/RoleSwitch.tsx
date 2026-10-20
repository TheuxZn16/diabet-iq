import type { Role } from "../types";

interface RoleSwitchProps {
  value: Role;
  onChange: (role: Role) => void;
}

export default function RoleSwitch({ value, onChange }: RoleSwitchProps) {
  return (
    <div className="flex bg-primary-50 border border-primary-100 rounded-xl p-1 mb-6">
      <button
        type="button"
        onClick={() => onChange("paciente")}
        className={`flex-1 flex items-center justify-center gap-2 py-2.5 rounded-[9px] text-sm font-semibold transition-colors ${
          value === "paciente"
            ? "bg-white text-primary-700 shadow-soft"
            : "text-ink-600"
        }`}
      >
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
          <circle cx="12" cy="8" r="3.4" />
          <path d="M5 20c0-3.9 3.1-7 7-7s7 3.1 7 7" />
        </svg>
        Sou paciente
      </button>
      <button
        type="button"
        onClick={() => onChange("medico")}
        className={`flex-1 flex items-center justify-center gap-2 py-2.5 rounded-[9px] text-sm font-semibold transition-colors ${
          value === "medico"
            ? "bg-white text-primary-700 shadow-soft"
            : "text-ink-600"
        }`}
      >
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
          <path d="M9 3h6v4a3 3 0 0 1-6 0V3Z" />
          <path d="M5 21v-3a6 6 0 0 1 6-6h2a6 6 0 0 1 6 6v3" />
          <path d="M12 13v5" />
        </svg>
        Sou médico
      </button>
    </div>
  );
}
