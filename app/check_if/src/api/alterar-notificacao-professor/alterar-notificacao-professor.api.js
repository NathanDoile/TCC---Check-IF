import { axiosInstance } from "../base/axiosInstance";

export async function alterarNotificacaoProfessorApi(id, tipoNotificacao) {
  try {
    await axiosInstance.put("/professores/alterar-notificacao", {
      id,
      tipoNotificacao,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
