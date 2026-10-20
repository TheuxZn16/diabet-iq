import { useForm } from "react-hook-form";
import { PrimaryButton } from "./Buttons";
import { FieldRow, FormField, SelectField } from "./FormField";
import { tipoDiabetesOptions, type pacienteForm } from "../types";

function PatientForm() {
  const { register, handleSubmit, reset } = useForm<pacienteForm>();

  const onSubmit = (data: pacienteForm) => {
    console.log(data);
    reset();
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <FormField label="Nome completo" type="text" placeholder="Maria da Silva" minLength={3} maxLength={150} required {...register("nome")} />
      <FormField label="E-mail" type="email" placeholder="maria@email.com" maxLength={150} required {...register("email")} />
      <FormField label="Senha" type="password" placeholder="Mínimo 8 caracteres" minLength={8} maxLength={100} required {...register("senha")} />
      <FieldRow>
        <FormField label="Data de nascimento" type="date" required {...register("dataNascimento")} />
        <SelectField options={tipoDiabetesOptions} label="Tipo de Diabetes" {...register("tipoDiabetes")} />
      </FieldRow>
      <FieldRow>
        <FormField label="Peso (kg)" type="number" placeholder="Ex: 68" min={1} max={500} step="0.1" required {...register("pesoKg")} />
        <FormField label="Altura (cm)" type="number" placeholder="Ex: 170" min={30} max={272} required {...register("alturaCm")} />
      </FieldRow>
      <FieldRow>
        <FormField label="Glicemia alvo — mínima" type="number" placeholder="Ex: 80" min={40} required {...register("glicemiaAlvoMin")} />
        <FormField label="Glicemia alvo — máxima" type="number" placeholder="Ex: 140" max={400} required {...register("glicemiaAlvoMax")} />
      </FieldRow>
      <PrimaryButton type="submit" className="mt-1.5">
        Criar conta
      </PrimaryButton>
    </form>
  );
}

export default PatientForm;