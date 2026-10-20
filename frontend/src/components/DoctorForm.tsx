import type { medicoForm } from "../types";
import { PrimaryButton } from "./Buttons";
import { FieldRow, FormField } from "./FormField";
import { useForm } from "react-hook-form";



function DoctorForm() {
    const { register, handleSubmit, reset } = useForm<medicoForm>();

    const onSubmit = (data: medicoForm) => {
        console.log(data);
        reset();
    };

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <FormField label="Nome completo" type="text" placeholder="Dr. João Pereira" minLength={3} maxLength={150} required {...register("nome")} />
            <FormField label="E-mail profissional" type="email" placeholder="joao.pereira@clinica.com" maxLength={150} required {...register("email")} />
            <FormField label="Senha" type="password" placeholder="Mínimo 8 caracteres" minLength={8} maxLength={100} required {...register("senha")} />
            <FieldRow>
                <FormField label="CRM" type="text" placeholder="123456" inputMode="numeric" pattern="\d{4,6}" maxLength={6} hint="4 a 6 dígitos, sem pontos ou traços." required  {...register("crm")} />
                <FormField label="UF do CRM" type="text" placeholder="SP" pattern="[A-Z]{2}" maxLength={2} className="uppercase" hint="2 letras maiúsculas." required {...register("ufCrm")} />
            </FieldRow>
            <FormField label="Especialidade" type="text" placeholder="Endocrinologia" maxLength={100} {...register("especialidade")} />
            <PrimaryButton type="submit" className="mt-1.5">
                Criar conta
            </PrimaryButton>
        </form>
    );
}

export default DoctorForm;