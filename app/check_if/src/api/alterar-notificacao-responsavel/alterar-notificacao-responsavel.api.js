import { axiosInstance } from "../base/axiosInstance";

export async function alterarNotificacaoResponsavelApi(
  idAluno,
  tipoNotificacao
) {
  try {
    await axiosInstance.put("/responsaveis/alterar-notificacao", {
      idAluno,
      tipoNotificacao,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
