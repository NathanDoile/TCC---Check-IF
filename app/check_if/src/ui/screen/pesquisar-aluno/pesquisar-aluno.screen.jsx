import "./pesquisar-aluno.screen.css";
import { useParams } from "react-router-dom";
import { Cabecalho, Navbar, Aluno } from "../../component";
import lupa from "../../../assets/images/Lupa-marrom.svg";

export function TelaPesquisarAluno() {
  const { texto } = useParams();

  console.log(texto);

  return (
    <>
      <Cabecalho />

      <main className="main-pesquisar-aluno">
        <form className="form-pesquisar-aluno-tela">
          <label className="label-pesquisar-tela-aluno">
            <input
              type="search"
              name="aluno"
              className="input-pesquisar-aluno-tela"
              onChange={() => {}}
              required
              defaultValue={texto}
            />
            <button className="botao-pesquisar-tela-aluno">
              <img src={lupa} alt="Lupa" className="lupa-icone" />
            </button>
          </label>
        </form>

        <div className="lista-aluno">
          <Aluno
            nome="Nathan de Souza Doile"
            turma="4K"
            matricula="078790INFQ"
            dataNascimento="29/07/2004"
          />
          <Aluno
            nome="Emily Aparecida da Silveira Eberhardt"
            turma="4I"
            matricula="078630INFQ"
            dataNascimento="30/12/2004"
          />
        </div>

        <Navbar paginaAtual={0} numeroPaginas={1} alterarPagina={() => {}} />
      </main>
    </>
  );
}
