export type Role = "paciente" | "medico";

export type Status = "ok" | "warn" | "high";

export interface Patient {
  initials: string;
  name: string;
  age: number;
  type: string;
  last: number;
  status: Status;
  trend: string;
  series: number[];
  avg: number;
  target: number;
}

export interface GlicemiaRecord {
  date: string;
  time: string;
  value: number;
  context: string;
  status: Status;
}

export interface medicoForm {
  nome: string;
  email: string;
  senha: string;
  crm: string;
  ufCrm: string;
  especialidade: string;
}

export interface pacienteForm {
  nome: string;
  email: string;
  senha: string;
  dataNascimento: Date;
  tipoDiabetes: typeof tipoDiabetesOptions;
  pesoKg: number;
  alturaCm: number;
  glicemiaAlvoMin: number;
  glicemiaAlvoMax: number;
}

export const tipoDiabetesOptions = [
  { value: "TIPO_1", label: "Tipo 1" },
  { value: "TIPO_2", label: "Tipo 2" },
  { value: "GESTACIONAL", label: "Gestacional" },
  { value: "LADA", label: "LADA" },
  { value: "MODY", label: "MODY" },
  { value: "OUTRO", label: "Outro" },
];
