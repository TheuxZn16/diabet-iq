import type { ReactNode } from "react";

interface MetricCardProps {
  label: string;
  value: string | number;
  unit?: string;
  badge?: ReactNode;
  valueClassName?: string;
}

export default function MetricCard({ label, value, unit, badge, valueClassName = "" }: MetricCardProps) {
  return (
    <div className="bg-white border border-hairline rounded-card px-4 py-4">
      <div className="text-xs font-semibold text-ink-600 mb-2.5">{label}</div>
      <div className={`text-2xl font-display font-extrabold text-ink-900 ${valueClassName}`}>
        {value}
        {unit && <small className="text-[13px] font-semibold text-ink-400 ml-1">{unit}</small>}
      </div>
      {badge}
    </div>
  );
}
