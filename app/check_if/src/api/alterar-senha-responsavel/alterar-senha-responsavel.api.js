import { axiosInstance } from "../base/axiosInstance";

export async function alterarSenhaResponsavelApi(senhaAntiga, senhaNova) {
  try {
    await axiosInstance.put("/responsaveis/senha", {
      senhaAntiga,
      senhaNova,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
