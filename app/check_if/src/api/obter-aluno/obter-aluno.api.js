import { axiosInstance } from "../base/axiosInstance";

export async function obterAlunoApi(id) {
  try {
    const response = await axiosInstance.get(`/alunos/${id}/obter`);

    return response.data;
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
