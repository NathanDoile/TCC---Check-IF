import "./tela-registrar-chegada-administrador.screen.css";
import { Cabecalho, TituloTelasIniciais, Input, Botao } from "../../component";
import { useState, useEffect } from "react";
import { useObterProfessores } from "../../../hooks";
import { toast } from "react-toastify";
import { useRegistrarChegadaManual } from "../../../hooks";

export function TelaRegistrarChegadaAdministrador() {

  const [carregando, setCarregando] = useState(false);

  const { obterProfessores } = useObterProfessores();

  const { registrarChegadaManual } = useRegistrarChegadaManual();

  const [formInput, setFormInput] = useState({
    professor: 0,
    disciplina: "",
    motivo: "",
    matricula: "",
  });

  const [outroMotivo, setOutroMotivo] = useState(false);

  const [professores, setProfessores] = useState([]);

  const [nomesProfessores, setNomesProfessores] = useState([]);

  const [idsProfessores, setIdsProfessores] = useState([]);

  const motivos = ["Despertador não tocou", "Problema no trânsito", "Outro"];

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

  async function registrarChegada(event) {
    event.preventDefault();

    if (
      formInput.professor === 0 ||
      formInput.motivo === "Selecione" ||
      formInput.matricula === "" ||
      formInput.motivo === ""
    ) {
      toast.error("Preencha todos os campos obrigatórios!");
    } else {

      setCarregando(true);

      await registrarChegadaManual(
        formInput.motivo,
        formInput.disciplina,
        formInput.matricula,
        formInput.professor
      );

      setCarregando(false);
    }
  }

  useEffect(() => {
    async function obter() {
      setProfessores([]);

      const response = await obterProfessores();

      setProfessores(response);
    }

    obter();
  }, []);

  useEffect(() => {
    setNomesProfessores([]);
    setIdsProfessores([]);

    professores.forEach((professor) => {
      setNomesProfessores((oldNomesProfessores) => [
        ...oldNomesProfessores,
        professor.nome,
      ]);
      setIdsProfessores((oldIdsProfessores) => [
        ...oldIdsProfessores,
        professor.id,
      ]);
    });
  }, [professores]);

  return (
    <>
      <Cabecalho />

      <main className="main-tela-registrar-chegada-adm">
        <TituloTelasIniciais>Registrar chegada atrasada</TituloTelasIniciais>

        <form
          onSubmit={registrarChegada}
          className="form-registrar-chegada-atrasada-adm"
        >
          <Input
            isSelect={false}
            legenda="Matrícula do aluno"
            isObrigatorio={true}
            name="matricula"
            handleChange={handleChange}
            type="text"
            grande={true}
          />

          <span className="inputs-form-registrar-chegada-adm">
            <Input
              isSelect={true}
              legenda="Professor"
              isObrigatorio={true}
              name="professor"
              handleChange={handleChange}
              opcoes={nomesProfessores}
              values={idsProfessores}
            />

            <Input
              isSelect={false}
              legenda={"Disciplina"}
              isObrigatorio={false}
              name={"disciplina"}
              handleChange={handleChange}
              type={"text"}
              grande={false}
            />
          </span>

          <span className="inputs-form-registrar-chegada-adm">
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

          <span className="botao-form-registrar-chegada-adm">
            <Botao cor={"laranja"} carregando={carregando}>Registrar</Botao>
          </span>
        </form>
      </main>
    </>
  );
}
