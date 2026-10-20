import type { Status } from "../types";

interface BadgeProps {
  status: Status;
  children: React.ReactNode;
  className?: string;
}

const styles: Record<Status, string> = {
  ok: "bg-success-100 text-success-600",
  warn: "bg-warning-100 text-warning-600",
  high: "bg-danger-100 text-danger-600",
};

const dotStyles: Record<Status, string> = {
  ok: "bg-success-600",
  warn: "bg-warning-600",
  high: "bg-danger-600",
};

export default function Badge({ status, children, className = "" }: BadgeProps) {
  return (
    <span
      className={`inline-flex items-center gap-1.5 text-xs font-bold px-2.5 py-1 rounded-full ${styles[status]} ${className}`}
    >
      <span className={`w-1.5 h-1.5 rounded-full ${dotStyles[status]}`} />
      {children}
    </span>
  );
}
