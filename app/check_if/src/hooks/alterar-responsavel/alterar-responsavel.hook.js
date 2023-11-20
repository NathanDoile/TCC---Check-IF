import { alterarResponsavelApi } from "../../api";
import { toast } from "react-toastify";

export function useAlterarResponsavel() {
  async function alterarResponsavel(nome, email, celular) {
    try {
      await alterarResponsavelApi(nome, email, celular);

      toast.success("Alteração realizada com sucesso!");
    } catch (error) {
      toast.error(error);
    }
  }

  return { alterarResponsavel };
}
