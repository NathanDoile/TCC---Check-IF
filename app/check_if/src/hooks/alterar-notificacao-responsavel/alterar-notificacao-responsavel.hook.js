import { alterarNotificacaoResponsavelApi } from "../../api";
import { toast } from "react-toastify";

export function useAlterarNotificacaoResponsavel() {
  async function alterarNotificacaoResponsavel(idAluno, tipoNotificacao) {
    try {
      await alterarNotificacaoResponsavelApi(idAluno, tipoNotificacao);

      toast.success("Alteração realizada com sucesso!");
    } catch (error) {
      toast.error(error);
    }
  }

  return { alterarNotificacaoResponsavel };
}
