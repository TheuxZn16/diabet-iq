import type { InputHTMLAttributes } from "react";
import type { tipoDiabetesOptions } from "../pages/CadastroPage";

interface FormFieldProps extends InputHTMLAttributes<HTMLInputElement> {
  label: string;
  hint?: string;
}

export function FormField({ label, hint, className = "", ...rest }: FormFieldProps) {
  return (
    <div className="mb-4">
      <label className="block text-sm font-semibold text-ink-700 mb-1.5">{label}</label>
      <input
        className={`w-full px-3.5 py-3 rounded-ctrl border border-ink-200 bg-white text-[15px] text-ink-900 outline-none placeholder:text-ink-400 focus:border-primary-500 focus:ring-4 focus:ring-primary-100 transition ${className}`}
        {...rest}
      />
      {hint && <p className="text-xs text-ink-400 mt-1.5">{hint}</p>}
    </div>
  );
}

interface SelectFieldProps {
  label?: string;
  options: typeof tipoDiabetesOptions;
}

export function SelectField({ label, options, ...rest }: SelectFieldProps) {
  return (
    <div className="mb-4">
      <label className="block text-sm font-semibold text-ink-700 mb-1.5">{label}</label>
      <select className="w-full px-3.5 py-3 rounded-ctrl border border-ink-200 bg-white text-[15px] text-ink-900 outline-none focus:border-primary-500 focus:ring-4 focus:ring-primary-100 transition" {...rest}>
        {options.map((opt) => (
          <option key={opt.value}>{opt.value}</option>
        ))}
      </select>
    </div>
  );
}

export function FieldRow({ children }: { children: React.ReactNode }) {
  return <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">{children}</div>;
}
