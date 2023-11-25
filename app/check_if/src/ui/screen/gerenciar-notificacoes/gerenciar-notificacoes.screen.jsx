import "./gerenciar-notificacoes.screen.css";
import { Cabecalho, TituloTelasIniciais, Notificacao } from "../../component";
import useGlobalUsuario from "../../../context/usuario/usuario.context";

export function TelaGerenciarNotificacoes() {
  const [usuario] = useGlobalUsuario();

  return (
    <>
      <Cabecalho />

      <main className="main-gerenciar-notificacoes">
        <TituloTelasIniciais>Gerenciar notificações</TituloTelasIniciais>

        <div className="container-notificacoes-responsavel">
          <Notificacao
            nome="Nathan de Souza Doile"
            turma="4K"
            matricula="078790INFQ"
            numero={usuario.celular}
            email={usuario.email}
            notificacaoEmail
          />

          <Notificacao
            nome="Emily Aparecida da Silveira Eberhardt"
            turma="4I"
            matricula="078630INFQ"
            numero={usuario.celular}
            email={usuario.email}
            notificacaoNumero
          />
        </div>
      </main>
    </>
  );
}
