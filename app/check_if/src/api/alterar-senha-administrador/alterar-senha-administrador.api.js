import { axiosInstance } from "../base/axiosInstance";

export async function alterarSenhaAdministradorApi(senhaAtual, novaSenha) {
  try {
    await axiosInstance.put("/administradores/alterar-senha", {
      senhaAtual,
      novaSenha,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
