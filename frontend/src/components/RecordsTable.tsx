import type { GlicemiaRecord } from "../types";
import Badge from "./Badge";

const statusLabel: Record<GlicemiaRecord["status"], string> = {
  ok: "Ok",
  warn: "Atenção",
  high: "Alto",
};

export default function RecordsTable({ records }: { records: GlicemiaRecord[] }) {
  return (
    <table className="w-full border-collapse text-[13px]">
      <thead>
        <tr>
          {["Data", "Hora", "Valor", "Contexto", "Status"].map((h) => (
            <th
              key={h}
              className="text-left text-ink-400 font-semibold text-xs pb-2.5 border-b border-hairline"
            >
              {h}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {records.map((r, i) => (
          <tr key={i}>
            <td className="py-2.5 border-b border-hairline text-ink-700 last:border-0">{r.date}</td>
            <td className="py-2.5 border-b border-hairline text-ink-700">{r.time}</td>
            <td className="py-2.5 border-b border-hairline text-ink-700">{r.value} mg/dL</td>
            <td className="py-2.5 border-b border-hairline text-ink-700">{r.context}</td>
            <td className="py-2.5 border-b border-hairline">
              <Badge status={r.status}>{statusLabel[r.status]}</Badge>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
