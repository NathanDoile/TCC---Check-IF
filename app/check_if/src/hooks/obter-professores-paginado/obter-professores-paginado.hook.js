import { obterProfessoresPaginadoApi } from "../../api";
import { toast } from "react-toastify";

export function useObterProfessoresPaginado() {
  async function obterProfessores(page) {
    try {
      const response = await obterProfessoresPaginadoApi(page);

      return response;
    } catch (error) {
      toast.error(error);
    }
  }

  return { obterProfessores };
}
