import "./perfil-aluno.screen.css";
import { useParams } from "react-router-dom";
import { Cabecalho, Botao } from "../../component";
import usuario from "../../../assets/images/UserCirculo-marrom.svg";
import calendario from "../../../assets/images/Calendario-marrom.svg";
import { useObterAluno, useGerarRelatorioAluno } from '../../../hooks';
import { useEffect, useState, useRef } from "react";

export function TelaPerfilAluno() {

  const [carregando, setCarregando] = useState(false);

  const ano = new Date(Date.now()).toISOString().substring(0, 4);

  const { id } = useParams();

  const { obterAluno } = useObterAluno();

  const { gerarRelatorioAluno } = useGerarRelatorioAluno();

  const [aluno, setAluno] = useState();

  const [formInput, setFormInput] = useState({
    mes: "",
    ano: ano
  })

  const [relatorio, setRelatorio] = useState();

  const [relatorioUrl, setRelatorioUrl] = useState();

  const relatorioRef = useRef(null);

  function handleChange(event) {

    const { name, value } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }))
  }

  async function handleSubmit() {

    setCarregando(true);

    const response = await gerarRelatorioAluno(aluno.id, formInput.mes, formInput.ano);

    setCarregando(false);

    setRelatorio(response);
  }

  useEffect(() => {

    async function obter() {
      const response = await obterAluno(id);

      setAluno(response);
    }

    obter()
  }, [])

  useEffect(() => {

    if (relatorio) {

      const relatorioLink = window.URL.createObjectURL(new Blob([relatorio], { type: "application/pdf" }));

      setRelatorioUrl(relatorioLink);

    }

  }, [relatorio])

  useEffect(() => {

    if (relatorioUrl && relatorioRef) {
      relatorioRef.current.click();

      setCarregando(false);
    }

  }, [relatorioUrl])

  return (
    <>
      <Cabecalho />

      <main className="main-perfil-aluno">
        <div className="dados-perfil-aluno">
          <h1>{aluno?.nome}</h1>

          <img className="imagem-usuario-perfil" src={usuario} alt="Usuário" />

          <span className="dados-perfil-aluno-especifico">
            <p>
              <b>Matrícula:</b> {aluno?.matricula}
            </p>

            <p>
              <b>Turma:</b> {aluno?.turma}
            </p>

            {aluno?.responsaveis.length > 0
              ?
              <p>
                <b>Responsável:</b>
                <br />
                {aluno.responsaveis.map(responsavel => 
                  {
                    return <span>{responsavel}<br /></span>
                  })
                }
              </p>
              : null}

          </span>
        </div>

        <div className="div-relatorio">
          <h1 className="titulo-relatorio">Relatório</h1>

          <p>Filtre por ano ou por mês e ano</p>

          <span className="div-form-relatorio">
            <img
              src={calendario}
              alt="Calendário"
              className="imagem-calendario"
            />

            <form
              className="form-relatorio"
            >
              <label>
                Mês:
                <input
                  type="number"
                  min={0}
                  max={12}
                  className="input-relatorio"
                  name="mes"
                  onChange={handleChange}
                />
              </label>

              <label>
                Ano:
                <input
                  type="number"
                  min={2008}
                  max={ano}
                  className="input-relatorio"
                  defaultValue={ano}
                  required
                  name="ano"
                  onChange={handleChange}
                />
              </label>
            </form>
          </span>

          <Botao cor="laranja" onClick={handleSubmit} carregando={carregando}>Exportar</Botao>
        </div>

        {relatorioUrl
          ? <a className="relatorio-gerado" href={relatorioUrl} download={`Relatório ${aluno.nome}`} ref={relatorioRef}></a>
          : null}
      </main>
    </>
  );
}
