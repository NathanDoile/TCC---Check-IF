import { alterarNotificacaoProfessorApi } from "../../api";


export function useAlterarNotificacaoProfessor() {

  async function alterarNotificacaoProfessor(id) {

    await alterarNotificacaoProfessorApi(id);

  }

  return { alterarNotificacaoProfessor };
}
