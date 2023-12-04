import { obterProfessoresApi } from "../../api";
import { toast } from "react-toastify";

export function useObterProfessores() {
  async function obterProfessores() {
    try {
      const response = await obterProfessoresApi();

      return response;
    } catch (error) {
      toast.error(error);
    }
  }

  return { obterProfessores };
}
