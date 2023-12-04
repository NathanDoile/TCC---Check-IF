import "./home-responsavel.screen.css";
import {
  Cabecalho,
  TituloTelasIniciais,
  Input,
  Botao,
} from "../../../component";
import { useState, useEffect } from "react";
import { useObterAlunoPorResponsavel, useSolicitarSaidaAntecipada } from "../../../../hooks";
import { toast } from "react-toastify";

export function TelaHomeResponsavel() {

  const [carregando, setCarregando] = useState(false);
  
  const [formInput, setFormInput] = useState({
    aluno: 0,
    data: "",
    hora: "",
    motivo: "",
  });

  const [outroMotivo, setOutroMotivo] = useState(false);

  const [alunos, setAlunos] = useState([]);

  const [idAlunos, setIdAlunos] = useState([]);

  const [nomeAlunos, setNomeAlunos] = useState([]);

  const { obterAlunosPorResponsavel } = useObterAlunoPorResponsavel();

  const { solicitarSaidaAntecipada } = useSolicitarSaidaAntecipada();

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

  async function handleSubmit(event){
    event.preventDefault();
    
    if(formInput.aluno === "0" || formInput.data === "" || formInput.hora === "" || formInput.motivo === ""){
      toast.error("Preencha todos os campos.");
    }
    else{

      setCarregando(true);

      await solicitarSaidaAntecipada(formInput.data, formInput.hora, formInput.motivo, formInput.aluno);

      setCarregando(false);
    }
  }

  useEffect(() => {

    async function obterAlunos(){

      const response = await obterAlunosPorResponsavel();
      
      setAlunos(response);
    }
    
    obterAlunos();
  }, []);  

  useEffect(() => {

    setIdAlunos([]);
    setNomeAlunos([]);

    alunos.forEach(aluno => {

      setIdAlunos((oldIdAlunos) => [...oldIdAlunos, aluno.id]);
      setNomeAlunos((oldNomeAlunos) => [...oldNomeAlunos, aluno.nome]);
    })

  }, [alunos]);

  return (
    <>
      <Cabecalho />

      <main className="main-home-responsavel">
        <TituloTelasIniciais>Solicite uma saída antecipada</TituloTelasIniciais>

        <form className="form-saida-antecipada" onSubmit={handleSubmit}>
          <Input
            isSelect
            legenda="Aluno"
            isObrigatorio
            name="aluno"
            handleChange={handleChange}
            opcoes={nomeAlunos}
            values={idAlunos}
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

          <Botao cor="laranja" carregando={carregando}>Solicitar</Botao>
        </form>
      </main>
    </>
  );
}
