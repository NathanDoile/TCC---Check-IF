import "./gerenciar-professores.screen.css";
import { Cabecalho, Navbar, Professor } from "../../component";
import adicionarImg from "../../../assets/images/Adicionar-marrom.svg";
import { useNavigate } from "react-router-dom";

export function TelaGerenciarProfessores() {
  const navigate = useNavigate();

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
          <Professor
            nome="Lourenço"
            siape="123456"
            numero="51999999999"
            email="professor@gmail.com"
            notificacaoNumero
            notificacaoEmail
          />
        </div>

        <Navbar paginaAtual={0} numeroPaginas={1} alterarPagina={() => {}} />
      </main>
    </>
  );
}
