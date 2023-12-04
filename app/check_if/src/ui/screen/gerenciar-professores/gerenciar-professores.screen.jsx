import "./gerenciar-professores.screen.css";
import { Cabecalho, Navbar, Professor } from "../../component";
import adicionarImg from "../../../assets/images/Adicionar-marrom.svg";
import { useNavigate } from "react-router-dom";
import { useObterProfessoresPaginado } from '../../../hooks';
import { useEffect, useState } from "react";
import loading from '../../../assets/images/loading.svg';

export function TelaGerenciarProfessores() {

  const [carregando, setCarregando] = useState(false);

  const navigate = useNavigate();

  const [professores, setProfessores] = useState([]);

  const [professoresTag, setProfessoresTag] = useState([]);

  const [pagina, setPagina] = useState(0);

  const [numeroPaginas, setNumeroPaginas] = useState();

  const { obterProfessores } = useObterProfessoresPaginado();

  useEffect(() => {

    async function obter() {

      setCarregando(true);

      const response = await obterProfessores(pagina);

      setCarregando(false);

      const listaProfessores = response.content;

      setProfessores([...listaProfessores]);
      setNumeroPaginas(response.totalPages);
    }

    obter();
  }, [pagina]);

  useEffect(() => {

    setProfessoresTag([]);

    professores.forEach((professor) => {
      setProfessoresTag((oldProfessoresTag) => ([...oldProfessoresTag,
      <Professor
        nome={professor.nome}
        siape={professor.siape}
        email={professor.email}
        notificacaoEmail={professor.notificacaoEmail}
        id={professor.id}
      />
      ]))
    });
  }, [professores])

  return (
    <>
      <Cabecalho />

      <main className="main-tela-gerenciar-professores">
        <div className="cabecalho-gerenciar-professores">

          <img
            src={adicionarImg}
            alt="Adicionar"
            className="gerenciar-professores-adicionar-imagem"
            onClick={() => {
              navigate("/adicionar-professor");
            }}
          />

          Adicionar professores

          {carregando ? <img src={loading} className="loading-botao" /> : null}
        </div>

        <div className="lista-professores">
          {professoresTag}
        </div>

        <Navbar paginaAtual={pagina} numeroPaginas={numeroPaginas} alterarPagina={setPagina} />
      </main>
    </>
  );
}
