import "./perfil-aluno.screen.css";
import { useParams } from "react-router-dom";
import { Cabecalho, Botao } from "../../component";
import usuario from "../../../assets/images/UserCirculo-marrom.svg";
import calendario from "../../../assets/images/Calendario-marrom.svg";

export function TelaPerfilAluno() {
  const { id } = useParams();

  const ano = new Date(Date.now()).toISOString().substring(0, 4);

  return (
    <>
      <Cabecalho />

      <main className="main-perfil-aluno">
        <div className="dados-perfil-aluno">
          <h1>Nathan de Souza Doile</h1>

          <img className="imagem-usuario-perfil" src={usuario} alt="Usuário" />

          <span>
            <p>
              <b>Matrícula:</b> 078790INFQ
            </p>

            <p>
              <b>Turma:</b> 4K
            </p>

            <p>
              <b>Responsável:</b>
              <br />
              Simone de Souza Doile
            </p>
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
              onSubmit={(e) => {
                e.preventDefault();
              }}
            >
              <label>
                Mês:
                <input
                  type="number"
                  min={0}
                  max={12}
                  className="input-relatorio"
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
                />
              </label>
            </form>
          </span>

          <Botao cor="laranja">Exportar</Botao>
        </div>
      </main>
    </>
  );
}
