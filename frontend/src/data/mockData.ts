import type { Patient, GlicemiaRecord } from "../types";

export const patients: Patient[] = [
  {
    initials: "AC",
    name: "Ana Clara Ferreira",
    age: 34,
    type: "Tipo 1",
    last: 98,
    status: "ok",
    trend: "-2",
    series: [110, 102, 120, 95, 108, 98, 98],
    avg: 104,
    target: 82,
  },
  {
    initials: "RM",
    name: "Roberto Melo",
    age: 58,
    type: "Tipo 2",
    last: 212,
    status: "high",
    trend: "+18",
    series: [160, 175, 190, 168, 200, 205, 212],
    avg: 187,
    target: 41,
  },
  {
    initials: "LS",
    name: "Luiza Santos",
    age: 27,
    type: "Gestacional",
    last: 118,
    status: "ok",
    trend: "0",
    series: [112, 108, 120, 116, 110, 118, 118],
    avg: 114,
    target: 88,
  },
  {
    initials: "PC",
    name: "Paulo Costa",
    age: 46,
    type: "Tipo 2",
    last: 172,
    status: "warn",
    trend: "+6",
    series: [130, 142, 150, 160, 155, 168, 172],
    avg: 153,
    target: 58,
  },
  {
    initials: "FN",
    name: "Fernanda Nunes",
    age: 63,
    type: "Tipo 1",
    last: 105,
    status: "ok",
    trend: "-4",
    series: [118, 112, 109, 120, 110, 108, 105],
    avg: 112,
    target: 79,
  },
];

export const patientGlicemiaSeries = [102, 145, 98, 168, 110, 190, 118];
export const weekDayLabels = ["Sáb", "Dom", "Seg", "Ter", "Qua", "Qui", "Hoje"];

export const periodoLabels = ["Jejum", "Pós-café", "Almoço", "Jantar", "Dormir"];
export const periodoValues = [96, 138, 154, 172, 112];

export const recentRecords: GlicemiaRecord[] = [
  { date: "12 set", time: "07:20", value: 98, context: "Jejum", status: "ok" },
  { date: "12 set", time: "13:10", value: 154, context: "Pós-almoço", status: "warn" },
  { date: "11 set", time: "22:40", value: 112, context: "Antes de dormir", status: "ok" },
  { date: "11 set", time: "19:05", value: 203, context: "Pós-jantar", status: "high" },
];
