interface BarChartProps {
  labels: string[];
  values: number[];
  width?: number;
  height?: number;
  highThreshold?: number;
}

export default function BarChart({
  labels,
  values,
  width = 260,
  height = 200,
  highThreshold = 160,
}: BarChartProps) {
  const pad = 24;
  const gap = 10;
  const max = Math.max(...values) * 1.25;
  const barW = (width - pad * 2 - gap * (values.length - 1)) / values.length;

  return (
    <svg viewBox={`0 0 ${width} ${height}`} width="100%" height={height}>
      {values.map((v, i) => {
        const barH = (v / max) * (height - pad * 2);
        const bx = pad + i * (barW + gap);
        const by = height - pad - barH;
        return (
          <g key={i}>
            <rect
              x={bx}
              y={by}
              width={barW}
              height={barH}
              rx={5}
              fill={v > highThreshold ? "#D2574C" : "#4FA3D1"}
            />
            <text x={bx + barW / 2} y={height - 6} fontSize={9} fill="#8A97AA" textAnchor="middle">
              {labels[i]}
            </text>
            <text
              x={bx + barW / 2}
              y={by - 6}
              fontSize={10}
              fill="#1C2A3A"
              textAnchor="middle"
              fontWeight={600}
            >
              {v}
            </text>
          </g>
        );
      })}
    </svg>
  );
}
