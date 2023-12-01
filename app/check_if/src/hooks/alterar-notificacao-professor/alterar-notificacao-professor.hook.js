import { alterarNotificacaoProfessorApi } from "../../api";


export function useAlterarNotificacaoProfessor() {

  async function alterarNotificacaoProfessor(id, tipoNotificacao) {

    await alterarNotificacaoProfessorApi(id, tipoNotificacao);

  }

  return { alterarNotificacaoProfessor };
}
