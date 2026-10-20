import type { ButtonHTMLAttributes } from "react";

export function PrimaryButton({
  className = "",
  children,
  ...rest
}: ButtonHTMLAttributes<HTMLButtonElement>) {
  return (
    <button
      className={`w-full bg-primary-600 hover:bg-primary-700 text-white font-bold text-[15px] py-3.5 rounded-ctrl transition-colors ${className}`}
      {...rest}
    >
      {children}
    </button>
  );
}

export function SecondaryButton({
  className = "",
  children,
  ...rest
}: ButtonHTMLAttributes<HTMLButtonElement>) {
  return (
    <button
      className={`w-full flex items-center justify-center gap-2 bg-white border border-ink-200 hover:bg-primary-50 text-primary-700 font-bold text-sm py-2.5 rounded-ctrl transition-colors ${className}`}
      {...rest}
    >
      {children}
    </button>
  );
}
