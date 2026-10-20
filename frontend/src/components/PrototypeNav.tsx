import { NavLink } from "react-router-dom";

const tabs = [
  { to: "/cadastro", label: "1. Cadastro" },
  { to: "/login", label: "2. Login" },
  { to: "/paciente", label: "3. Home · Paciente" },
  { to: "/medico", label: "4. Home · Médico" },
];

export default function PrototypeNav() {
  return (
    <div className="sticky top-0 z-50 bg-ink-900 text-white px-5 py-3 flex items-center gap-4 flex-wrap">
      <span className="text-[11px] text-ink-200 tracking-wide whitespace-nowrap">
        DIABET IQ — PROTÓTIPO
      </span>
      <div className="flex gap-1.5 flex-wrap">
        {tabs.map((tab) => (
          <NavLink
            key={tab.to}
            to={tab.to}
            className={({ isActive }) =>
              `px-3.5 py-1.5 rounded-full text-[13px] font-medium border transition-colors ${
                isActive
                  ? "bg-primary-500 border-primary-500 text-[#062338]"
                  : "border-[#3A4A5E] text-[#CBD8E5] hover:bg-[#2A3A4E]"
              }`
            }
          >
            {tab.label}
          </NavLink>
        ))}
      </div>
    </div>
  );
}
