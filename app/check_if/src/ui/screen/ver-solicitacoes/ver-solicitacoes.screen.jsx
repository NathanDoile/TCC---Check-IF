import "./ver-solicitacoes.screen.css";
import {
  Cabecalho,
  TituloTelasIniciais,
  Navbar,
  SaidaAntecipadaResponsavel,
} from "../../component";
import { useVerSaidasAntecipadas } from "../../../hooks";
import { useState, useEffect } from "react";
import loading from '../../../assets/images/loading.svg';

export function TelaVerSolicitacoes() {

  const [carregando, setCarregando] = useState(false);

  const hoje = new Date(Date.now()).toISOString().substring(0, 10);

  const [data, setData] = useState(hoje);

  const { verSaidasAntecipadas } = useVerSaidasAntecipadas();

  const [saidasAntecipadas, setSaidasAntecipadas] = useState([]);

  const [saidasAntecipadasTag, setSaidasAntecipadasTag] = useState([]);

  const [pagina, setPagina] = useState(0);

  const [numeroPaginas, setNumeroPaginas] = useState();

  useEffect(() => {

    async function obter() {

      setCarregando(true);

      const response = await verSaidasAntecipadas(pagina, data);

      setCarregando(false);

      setSaidasAntecipadas(response.content);
      setNumeroPaginas(response.totalPages);

    }

    obter()
  }, [data, pagina]);

  useEffect(() => {

    setSaidasAntecipadasTag([]);

    saidasAntecipadas.forEach(saidaAntecipada => {

      setSaidasAntecipadasTag((oldSaidasAntecipadasTag) => [...oldSaidasAntecipadasTag,
        <SaidaAntecipadaResponsavel
          nome={saidaAntecipada.nome}
          turma={saidaAntecipada.turma}
          matricula={saidaAntecipada.matricula}
          data={saidaAntecipada.dataSaida}
          hora={saidaAntecipada.horaSaida}
        />
      ])
    })
  }, [saidasAntecipadas])

  return (
    <>
      <Cabecalho />

      <main className="main-ver-solicitacoes">
        <TituloTelasIniciais>
          Histórico de saídas antecipadas
        </TituloTelasIniciais>

        <form>
          {carregando ? <img src={loading} className="loading-botao" /> : null}
          <input
            type="date"
            name="data"
            className="input-data-solicitacoes"
            defaultValue={hoje}
            onChange={(e) => {setData(e.target.value)}}
          />
        </form>

        <div className="conteudo-saidas-antecipadas">
          {saidasAntecipadasTag.length > 0 
          ? saidasAntecipadasTag 
          : <TituloTelasIniciais>Nenhuma saída antecipada localizada</TituloTelasIniciais>}
        </div>

        <Navbar paginaAtual={pagina} numeroPaginas={numeroPaginas} alterarPagina={setPagina} />
      </main>
    </>
  );
}
