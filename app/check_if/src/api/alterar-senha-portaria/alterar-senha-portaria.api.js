import { axiosInstance } from "../base/axiosInstance";

export async function alterarSenhaPortariaApi(senhaAtual, novaSenha) {
  try {
    await axiosInstance.put("/portarias/alterar-senha", {
      senhaAtual,
      novaSenha,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
