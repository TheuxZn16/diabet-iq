interface LineChartProps {
  values: number[];
  targetMin: number;
  targetMax: number;
  labels?: string[];
  width?: number;
  height?: number;
}

export default function LineChart({
  values,
  targetMin,
  targetMax,
  labels,
  width = 560,
  height = 200,
}: LineChartProps) {
  const pad = 24;
  const min = Math.min(...values) - 20;
  const max = Math.max(...values) + 20;

  const x = (i: number) => pad + (i * (width - pad * 2)) / (values.length - 1);
  const y = (v: number) => height - pad - ((v - min) * (height - pad * 2)) / (max - min);

  const targetTop = y(targetMax);
  const targetBottom = y(targetMin);
  const points = values.map((v, i) => `${x(i)},${y(v)}`).join(" ");

  return (
    <svg viewBox={`0 0 ${width} ${height}`} width="100%" height={height}>
      <rect
        x={pad}
        y={targetTop}
        width={width - pad * 2}
        height={targetBottom - targetTop}
        fill="#DCEEFA"
        rx={4}
      />
      <polyline
        points={points}
        fill="none"
        stroke="#2E86C1"
        strokeWidth={2.5}
        strokeLinejoin="round"
        strokeLinecap="round"
      />
      {values.map((v, i) => (
        <g key={i}>
          <circle cx={x(i)} cy={y(v)} r={3.5} fill="#2E86C1" />
          {labels && (
            <text x={x(i)} y={height - 4} fontSize={10} fill="#8A97AA" textAnchor="middle">
              {labels[i]}
            </text>
          )}
        </g>
      ))}
    </svg>
  );
}
