import { axiosInstance } from "../base/axiosInstance";

export async function gerarRelatorioAlunoApi(id, mes, ano) {
  try {
    await axiosInstance.post("/alunos/gerar-relatorio", {
      id,
      mes,
      ano,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
