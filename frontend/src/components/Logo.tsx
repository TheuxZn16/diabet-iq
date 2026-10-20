interface LogoProps {
  size?: "sm" | "md";
}

export default function Logo({ size = "md" }: LogoProps) {
  const markSize = size === "sm" ? 28 : 34;
  const wordSize = size === "sm" ? "text-[17px]" : "text-xl";

  return (
    <div className="flex items-center gap-2.5">
      <svg width={markSize} height={markSize} viewBox="0 0 34 34" fill="none" xmlns="http://www.w3.org/2000/svg">
        <circle cx="17" cy="17" r="17" fill="#DCEEFA" />
        <path
          d="M17 6C17 6 10 14.5 10 20.2C10 24.1 13.1 27 17 27C20.9 27 24 24.1 24 20.2C24 14.5 17 6 17 6Z"
          fill="#2E86C1"
        />
        <path
          d="M14 21.5C14 23.4 15.3 24.8 17 24.8"
          stroke="#DCEEFA"
          strokeWidth="1.6"
          strokeLinecap="round"
        />
      </svg>
      <div className={`font-display font-bold text-ink-900 ${wordSize}`}>
        diabet<span className="text-primary-600">iQ</span>
      </div>
    </div>
  );
}
