interface TopBarProps {
  title: string;
  subtitle: string;
  initials: string;
  onMenuClick?: () => void;
}

export default function TopBar({ title, subtitle, initials, onMenuClick }: TopBarProps) {
  return (
    <div className="flex items-center justify-between gap-3 sm:gap-4 flex-wrap mb-5">
      <div className="flex items-center gap-3 min-w-0">
        {onMenuClick && (
          <button
            aria-label="Abrir menu"
            onClick={onMenuClick}
            className="md:hidden w-9 h-9 shrink-0 rounded-full border border-hairline bg-white flex items-center justify-center text-ink-600"
          >
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
              <path d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
        )}
        <div className="min-w-0">
          <h1 className="text-lg sm:text-[22px] font-display font-bold text-ink-900 truncate">{title}</h1>
          <p className="text-xs sm:text-sm text-ink-600 mt-1 truncate">{subtitle}</p>
        </div>
      </div>
      <div className="flex items-center gap-3.5 shrink-0">
        <button
          aria-label="Notificações"
          className="w-9 h-9 rounded-full border border-hairline bg-white flex items-center justify-center text-ink-600"
        >
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round">
            <path d="M18 8a6 6 0 0 0-12 0c0 7-3 9-3 9h18s-3-2-3-9" />
            <path d="M13.7 21a2 2 0 0 1-3.4 0" />
          </svg>
        </button>
        <div className="w-9 h-9 rounded-full bg-primary-100 text-primary-700 flex items-center justify-center text-[13px] font-bold">
          {initials}
        </div>
      </div>
    </div>
  );
}
