import "./gerenciar-professores.screen.css";
import { Cabecalho, Navbar, Professor } from "../../component";
import adicionarImg from "../../../assets/images/Adicionar-marrom.svg";
import { useNavigate } from "react-router-dom";
import { useObterProfessores } from '../../../hooks';
import { useEffect, useState } from "react";

export function TelaGerenciarProfessores() {

  const navigate = useNavigate();

  const [professores, setProfessores] = useState([]);

  const [professoresTag, setProfessoresTag] = useState([]);

  const [pagina, setPagina] = useState(0);

  const [numeroPaginas, setNumeroPaginas] = useState();

  const { obterProfessores } = useObterProfessores();

  useEffect(() => {

    async function obter() {

      const response = await obterProfessores(pagina);

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
        numero={professor.celular}
        email={professor.email}
        notificacaoNumero={professor.notificacaoWhatsapp}
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
        </div>

        <div className="lista-professores">
          {professoresTag}
        </div>

        <Navbar paginaAtual={pagina} numeroPaginas={numeroPaginas} alterarPagina={setPagina} />
      </main>
    </>
  );
}
