import { axiosInstance } from "../base/axiosInstance";

export async function alterarNotificacaoProfessorApi(id) {
  try {
    await axiosInstance.put("/professores/alterar-notificacao", {
      id
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
