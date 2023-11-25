import "./home-responsavel.screen.css";
import {
  Cabecalho,
  TituloTelasIniciais,
  Input,
  Botao,
} from "../../../component";
import { useState } from "react";

export function TelaHomeResponsavel() {
  const [formInput, setFormInput] = useState({
    aluno: 0,
    data: "",
    hora: "",
    motivo: "",
  });

  const [outroMotivo, setOutroMotivo] = useState(false);

  const motivos = [
    "Consulta médica",
    "Trabalhar",
    "Problema de saúde",
    "Imprevisto",
    "Problemas pessoais",
    "Outro",
  ];

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

  return (
    <>
      <Cabecalho />

      <main className="main-home-responsavel">
        <TituloTelasIniciais>Solicite uma saída antecipada</TituloTelasIniciais>

        <form className="form-saida-antecipada">
          <Input
            isSelect
            legenda="Aluno"
            isObrigatorio
            name="aluno"
            handleChange={handleChange}
            opcoes={[
              "Nathan de Souza Doile",
              "Emily Aparecida da Silveira Eberhardt",
            ]}
            values={[1, 2]}
            grande
          />

          <span className="inputs-solicitar-saida">
            <Input
              legenda="Data"
              isObrigatorio
              name="data"
              handleChange={handleChange}
              type="date"
            />

            <Input
              legenda="Hora"
              isObrigatorio
              name="hora"
              handleChange={handleChange}
              type="time"
            />
          </span>

          <span className="inputs-solicitar-saida">
            <Input
              isSelect={true}
              legenda="Motivo"
              isObrigatorio={true}
              name="motivo"
              handleChange={handleChange}
              opcoes={motivos}
              values={motivos}
            />

            {outroMotivo ? (
              <Input
                isSelect={false}
                legenda={"Outro"}
                isObrigatorio={true}
                name={"outroMotivo"}
                handleChange={handleChange}
                type={"text"}
                grande={false}
              />
            ) : null}
          </span>

          <Botao>Solicitar</Botao>
        </form>
      </main>
    </>
  );
}
