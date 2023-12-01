import { obterProfessoresApi } from "../../api";
import { toast } from "react-toastify";

export function useObterProfessores() {
  async function obterProfessores(page) {
    try {
      const response = await obterProfessoresApi(page);

      return response;
    } catch (error) {
      toast.error(error);
    }
  }

  return { obterProfessores };
}
