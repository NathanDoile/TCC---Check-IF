import "./tela-registrar-saida-antecipadas.screen.css";
import { Cabecalho, TituloTelasIniciais, Input, Botao } from "../../component";
import { useState } from "react";
import { toast } from "react-toastify";
import { useRegistrarSaidaAntecipada } from "../../../hooks";

export function TelaRegistrarSaidaAntecipada() {

    const { registrarSaidaAntecipada} = useRegistrarSaidaAntecipada();

    const [formInput, setFormInput] = useState({
        matricula: 0,
        responsavel: "",
        grauParentesco: "",
        motivo: "",
    });

    const [outroMotivo, setOutroMotivo] = useState(false);

    const motivos = ["Consulta médica", "Trabalhar", "Problema de saúde", "Imprevisto", "Problemas pessoais", "Outro"];

    function handleChange(event) {
        const { name, value } = event.target;

        if (name === "motivo" && value === "Outro") {
            setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: "" }));

            setOutroMotivo(true);
        } else if (name === "outroMotivo") {
            setFormInput((oldFormInput) => ({ ...oldFormInput, motivo: value }));
        } else if (name === "motivo" && value !== "Outro" && outroMotivo) {
            setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));

            setOutroMotivo(false);
        } else {
            setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
        }
    }

    async function registrarSaida(event) {
        event.preventDefault();
    
        if (
          formInput.responsavel === "" ||
          formInput.motivo === "Selecione" ||
          formInput.matricula === "" ||
          formInput.motivo === "" ||
          formInput.grauParentesco === ""
        ) {
          toast.error("Preencha todos os campos obrigatórios!");
        } else {
          await registrarSaidaAntecipada(formInput.responsavel, formInput.grauParentesco, formInput.motivo, formInput.matricula);
        }
      }

    return (
        <>
            <Cabecalho />

            <main className="main-registrar-saida-antecipada">

                <TituloTelasIniciais>Registrar saída antecipada</TituloTelasIniciais>

                <form className="form-registrar-saida-antecipada" onSubmit={registrarSaida}>

                    <Input legenda="Matrícula do aluno" isObrigatorio name="matricula" handleChange={handleChange} type="text" grande />

                    <span className="inputs-registrar-saida-antecipada">
                        <Input legenda="Responsável" isObrigatorio name="responsavel" handleChange={handleChange} type="text" />

                        <Input legenda="Parentesco" isObrigatorio name="grauParentesco" handleChange={handleChange} type="text" />
                    </span>

                    <span className="inputs-registrar-saida-antecipada">
                        <Input isSelect legenda="Motivo" isObrigatorio name="motivo" handleChange={handleChange} opcoes={motivos} 
                            values={motivos} />

                        {outroMotivo 
                        ? 
                            <Input legenda="Outro" isObrigatorio name="outroMotivo" handleChange={handleChange} type="text" />
                        : null}
                        
                    </span>

                    <Botao cor="laranja">Registrar</Botao>

                </form>

            </main>
        </>
    );
}
