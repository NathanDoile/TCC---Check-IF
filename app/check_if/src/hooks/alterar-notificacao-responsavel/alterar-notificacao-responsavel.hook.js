import { alterarNotificacaoResponsavelApi } from "../../api";

export function useAlterarNotificacaoResponsavel() {
  async function alterarNotificacaoResponsavel(idAluno, tipoNotificacao) {

      await alterarNotificacaoResponsavelApi(idAluno, tipoNotificacao);
  }

  return { alterarNotificacaoResponsavel };
}
