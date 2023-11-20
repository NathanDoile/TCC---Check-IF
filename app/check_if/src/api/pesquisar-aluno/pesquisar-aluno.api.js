import { axiosInstance } from "../base/axiosInstance";

export async function pesquisarAlunoApi(texto) {
  try {
    const response = await axiosInstance.get(
      `/alunos/pesquisar&texto=${texto}`
    );

    return response.data;
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
