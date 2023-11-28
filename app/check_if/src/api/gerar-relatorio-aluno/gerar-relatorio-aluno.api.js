import { axiosInstance } from "../base/axiosInstance";

export async function gerarRelatorioAlunoApi(id, mes, ano) {
  try {
    const response = await axiosInstance.post("/alunos/gerar-relatorio", {
      id,
      mes,
      ano,
    }, { responseType: 'blob' });

    return response.data;
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
