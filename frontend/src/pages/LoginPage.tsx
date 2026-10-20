import { Link } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";
import Logo from "../components/Logo";
import { FormField } from "../components/FormField";
import { PrimaryButton } from "../components/Buttons";

export default function LoginPage() {
  return (
    <AuthLayout>
      <div className="mb-7">
        <Logo />
      </div>

      <h1 className="text-2xl font-display font-bold text-ink-900 mb-1.5">Bem-vindo de volta</h1>
      <p className="text-sm text-ink-600 mb-6">Entre para continuar acompanhando sua glicemia.</p>

      <form>
        <FormField label="E-mail" type="email" placeholder="voce@email.com" />
        <FormField label="Senha" type="password" placeholder="Sua senha" />

        <div className="flex items-center gap-2 text-sm text-ink-600 mb-5">
          <input type="checkbox" id="keep" className="accent-primary-600" />
          <label htmlFor="keep">Manter conectado</label>
          <Link to="#" className="ml-auto text-primary-600 font-medium">
            Esqueci minha senha
          </Link>
        </div>

        <PrimaryButton type="button">Entrar</PrimaryButton>
      </form>

      <p className="text-center text-sm text-ink-600 mt-5">
        Não tem conta?{" "}
        <Link to="/cadastro" className="font-semibold text-primary-600">
          Cadastre-se
        </Link>
      </p>
    </AuthLayout>
  );
}
