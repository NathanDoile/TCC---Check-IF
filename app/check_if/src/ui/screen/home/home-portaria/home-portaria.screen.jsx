import "./home-portaria.screen.css";
import {
  Cabecalho,
  TituloTelasIniciais,
  Navbar,
  SaidaAntecipada,
} from "../../../component";
import { useObterSaidasAntecipadas } from "../../../../hooks";
import { useState, useEffect } from "react";
import loading from '../../../../assets/images/loading.svg';

export function TelaHomePortaria() {

  const [carregando, setCarregando] = useState(false);

  const { obterSaidasAntecipadas } = useObterSaidasAntecipadas();

  const [saidasAntecipadas, setSaidasAntecipadas] = useState([]);

  const [saidasAntecipadasTag, setSaidasAntecipadasTag] = useState([]);

  const [pagina, setPagina] = useState(0);

  const [numeroPaginas, setNumeroPaginas] = useState();

  const [recarregar, setRecarregar] = useState(false);
  
  useEffect(() => {

    async function obterSaidas() {

      setCarregando(true);
      
      const response = await obterSaidasAntecipadas(pagina);

      setCarregando(false);
      
      const saidas = response.content;
      setNumeroPaginas(response.totalPages);
      setSaidasAntecipadas([...saidas]);
    }

    obterSaidas();

  }, [pagina, recarregar])

  useEffect(() => {
    
    setSaidasAntecipadasTag([]);

    saidasAntecipadas.forEach(saidaAntecipada => {

      setSaidasAntecipadasTag((oldSaidasAntecipadasTag) => ([...oldSaidasAntecipadasTag,
        <SaidaAntecipada
          nome={saidaAntecipada.nome}
          turma={saidaAntecipada.turma}
          matricula={saidaAntecipada.matricula}
          data={saidaAntecipada.dataAutorizada}
          hora={saidaAntecipada.horaAutorizada}
          id={saidaAntecipada.id}
          recarregar={() => {setRecarregar(!recarregar)}}
        />
      ]))
    });

  }, [saidasAntecipadas])

  return (
    <>
      <Cabecalho />

      <main className="main-home-portaria">
        <TituloTelasIniciais>
          Solicitações de saídas antecipadas
        </TituloTelasIniciais>

        {carregando ? <img src={loading} className="loading-botao" /> : null}

        <div className="conteudo-saidas-antecipadas">
          {saidasAntecipadasTag.length > 0 ? saidasAntecipadasTag : <TituloTelasIniciais>Nenhuma saída antecipada para o dia de hoje</TituloTelasIniciais>}
        </div>

        <Navbar paginaAtual={pagina} numeroPaginas={numeroPaginas} alterarPagina={setPagina} />
      </main>
    </>
  );
}
