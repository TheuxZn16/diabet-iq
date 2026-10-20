import { useState } from "react";
import { Link } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";
import Logo from "../components/Logo";
import RoleSwitch from "../components/RoleSwitch";
import type { Role } from "../types";
import PatientForm from "../components/PatientForm";
import DoctorForm from "../components/DoctorForm";

export default function CadastroPage() {
  const [role, setRole] = useState<Role>("paciente");

  return (
    <AuthLayout>
      <div className="mb-7">
        <Logo />
      </div>

      <h1 className="text-2xl font-display font-bold text-ink-900 mb-1.5">Criar sua conta</h1>
      <p className="text-sm text-ink-600 mb-6">
        Leva menos de 2 minutos. Escolha como você vai usar o diabet iQ.
      </p>

      <RoleSwitch value={role} onChange={setRole} />

      {role === "paciente" ? (
        <PatientForm />
      ) : (
        <DoctorForm />
      )
      }

      <p className="text-center text-sm text-ink-600 mt-5">
        Já tem conta?{" "}
        <Link to="/login" className="font-semibold text-primary-600">
          Entrar
        </Link>
      </p>
    </AuthLayout >
  );
}