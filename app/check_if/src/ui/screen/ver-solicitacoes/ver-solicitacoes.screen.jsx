import "./ver-solicitacoes.screen.css";
import {
  Cabecalho,
  TituloTelasIniciais,
  Navbar,
  SaidaAntecipadaResponsavel,
} from "../../component";

export function TelaVerSolicitacoes() {
  const hoje = new Date(Date.now()).toISOString().substring(0, 10);

  return (
    <>
      <Cabecalho />

      <main className="main-ver-solicitacoes">
        <TituloTelasIniciais>
          Histórico de saídas antecipadas
        </TituloTelasIniciais>

        <form>
          <input
            type="date"
            name="data"
            className="input-data-solicitacoes"
            defaultValue={hoje}
            onChange={() => {}}
          />
        </form>

        <div className="conteudo-saidas-antecipadas">
          <SaidaAntecipadaResponsavel
            nome="Nathan de Souza Doile"
            turma="4K"
            matricula="078790INFQ"
            data="25/11/2023"
            hora="11:50"
          />

          <SaidaAntecipadaResponsavel
            nome="Emily Aparecida da Silveira Eberhardt"
            turma="4I"
            matricula="078630INFQ"
            data="25/11/2023"
            hora="09:50"
          />
        </div>

        <Navbar paginaAtual={0} numeroPaginas={1} alterarPagina={() => {}} />
      </main>
    </>
  );
}
