import "./home-administrador.screen.css";
import { Cabecalho, ChegadaAtrasada, Navbar } from "../../../component";
import adicionar from "../../../../assets/images/Adicionar-marrom.svg";
import { useObterChegadasAtrasadas } from "../../../../hooks";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export function TelaHomeAdministrador() {
  const navigate = useNavigate();

  const hoje = new Date(Date.now()).toISOString().substring(0, 10);

  const [data, setData] = useState(hoje);

  const { obterChegadasAtrasadas } = useObterChegadasAtrasadas();

  const [chegadasAtrasadas, setChegadasAtrasadas] = useState([]);

  const [chegadasAtrasadasTag, setChegadasAtrasadasTag] = useState([]);

  const [pagina, setPagina] = useState(0);

  const [numeroPaginas, setNumeroPaginas] = useState();

  const [registrarOcorrencia, setRegistrarOcorrencia] = useState(false);

  async function atualizarChegadas(date) {
    const response = await obterChegadasAtrasadas(date, pagina);

    const chegadas = response.content;

    setNumeroPaginas(response.totalPages);
    setChegadasAtrasadas([...chegadas]);
  }

  useEffect(() => {
    atualizarChegadas(data);
  }, [data, pagina]);

  useEffect(() => {
    setChegadasAtrasadasTag([]);

    chegadasAtrasadas.forEach((c) => {
      setChegadasAtrasadasTag((oldChegadasAtrasadasTag) => [
        ...oldChegadasAtrasadasTag,
        <ChegadaAtrasada
          nome={c.nome}
          turma={c.turma}
          matricula={c.matricula}
          data={c.data}
          hora={c.hora}
        />,
      ]);
    });
  }, [chegadasAtrasadas]);

  return (
    <>
      <Cabecalho />

      <main className="tela-home-administrador">
        <div className="cabecalho-chegadas-atrasadas">
          <span className="input-adicionar-registro">
            <img
              src={adicionar}
              alt="Adicionar"
              className="imagem-adicionar"
              onClick={() => {
                setRegistrarOcorrencia(!registrarOcorrencia);
              }}
            />

            {registrarOcorrencia ? (
              <span className="botoes-registrar">
                <button
                  className="botao-registrar"
                  onClick={() => {
                    navigate("/administrador/registrar-chegada-atrasada");
                  }}
                >
                  Chegada atrasada
                </button>
                <button
                  className="botao-registrar"
                  onClick={() => {
                    navigate("/registrar-saida-antecipada");
                  }}
                >
                  Saída antecipada
                </button>
              </span>
            ) : (
              <span>Cadastre ocorrências</span>
            )}
          </span>

          <h1 className="titulo-chegadas-atrasadas">
            Histórico de chegadas atrasadas
          </h1>

          <form>
            <input
              type="date"
              name="data"
              className="input-data"
              defaultValue={hoje}
              onChange={(e) => {
                setData(e.target.value);
              }}
            />
          </form>
        </div>

        <div className="conteudo-chegadas-atrasadas">
          {chegadasAtrasadasTag.length === 0 ? (
            <h2>Nenhum registro encontrado na data informada</h2>
          ) : (
            chegadasAtrasadasTag
          )}
        </div>

        <Navbar
          paginaAtual={pagina}
          numeroPaginas={numeroPaginas}
          alterarPagina={setPagina}
        />
      </main>
    </>
  );
}
