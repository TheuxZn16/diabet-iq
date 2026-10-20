import Logo from "./Logo";
import type { Role } from "../types";

interface NavLinkItem {
  label: string;
  icon: React.ReactNode;
  active?: boolean;
}

interface SidebarProps {
  role: Role;
  userName: string;
  userSubtitle: string;
  initials: string;
  open?: boolean;
  onClose?: () => void;
}

const pacienteLinks: NavLinkItem[] = [
  {
    label: "Início",
    active: true,
    icon: (
      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
        <path d="M3 11l9-8 9 8" />
        <path d="M5 10v10h14V10" />
      </svg>
    ),
  },
  {
    label: "Registros",
    icon: (
      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
        <path d="M12 20V10" />
        <path d="M18 20V4" />
        <path d="M6 20v-4" />
      </svg>
    ),
  },
  {
    label: "Relatórios",
    icon: (
      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
        <path d="M6 2h9l5 5v15H6z" />
        <path d="M9 12h6M9 16h6" />
      </svg>
    ),
  },
  {
    label: "Meu médico",
    icon: (
      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
        <path d="M9 3h6v4a3 3 0 0 1-6 0V3Z" />
        <path d="M5 21v-3a6 6 0 0 1 6-6h2a6 6 0 0 1 6 6v3" />
      </svg>
    ),
  },
];

const medicoLinks: NavLinkItem[] = [
  {
    label: "Pacientes",
    active: true,
    icon: (
      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
        <circle cx="9" cy="8" r="3" />
        <path d="M3 20c0-3.3 2.7-6 6-6s6 2.7 6 6" />
        <circle cx="17" cy="9" r="2.4" />
        <path d="M14.5 14.2c.6-.2 1.3-.3 2-.3 2.5 0 4.5 2 4.5 4.5" />
      </svg>
    ),
  },
  {
    label: "Solicitações",
    icon: (
      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
        <path d="M18 8a6 6 0 0 0-12 0c0 7-3 9-3 9h18s-3-2-3-9" />
      </svg>
    ),
  },
  {
    label: "Agenda",
    icon: (
      <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
        <rect x="3" y="4" width="18" height="16" rx="2" />
        <path d="M3 9h18" />
      </svg>
    ),
  },
];

const settingsLink: NavLinkItem = {
  label: "Configurações",
  icon: (
    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
      <circle cx="12" cy="12" r="3" />
      <path d="M4 12h1m14 0h1M12 4v1m0 14v1M6.3 6.3l.7.7m9.4 9.4l.7.7M6.3 17.7l.7-.7m9.4-9.4l.7-.7" />
    </svg>
  ),
};

export default function Sidebar({ role, userName, userSubtitle, initials, open = false, onClose }: SidebarProps) {
  const links = [...(role === "paciente" ? pacienteLinks : medicoLinks), settingsLink];

  return (
    <>
      {open && (
        <div
          className="fixed inset-0 bg-black/40 z-40 md:hidden"
          onClick={onClose}
          aria-hidden="true"
        />
      )}
      <aside
        className={`fixed md:static inset-y-0 left-0 z-50 w-64 md:w-56 shrink-0 bg-white border-r border-hairline p-5 flex flex-col transition-transform duration-200 ease-out md:translate-x-0 ${
          open ? "translate-x-0" : "-translate-x-full"
        }`}
      >
        <div className="flex items-center justify-between mb-8">
          <div className="px-1.5">
            <Logo size="sm" />
          </div>
          <button
            aria-label="Fechar menu"
            onClick={onClose}
            className="md:hidden w-8 h-8 flex items-center justify-center rounded-full text-ink-600 hover:bg-canvas"
          >
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
              <path d="M18 6L6 18M6 6l12 12" />
            </svg>
          </button>
        </div>
        {links.map((link) => (
          <a
            key={link.label}
            href="#"
            className={`flex items-center gap-2.5 px-3 py-2.5 rounded-[10px] text-sm font-semibold mb-0.5 ${
              link.active ? "bg-primary-100 text-primary-700" : "text-ink-600 hover:bg-canvas"
            }`}
          >
            {link.icon}
            {link.label}
          </a>
        ))}
        <div className="flex-1" />
        <div className="flex items-center gap-2.5 px-2 py-2.5 border-t border-hairline mt-2">
          <div className="w-9 h-9 rounded-full bg-primary-100 text-primary-700 flex items-center justify-center text-[13px] font-bold shrink-0">
            {initials}
          </div>
          <div>
            <div className="text-[13px] font-semibold text-ink-900">{userName}</div>
            <div className="text-xs text-ink-400">{userSubtitle}</div>
          </div>
        </div>
      </aside>
    </>
  );
}
