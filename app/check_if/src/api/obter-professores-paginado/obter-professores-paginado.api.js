import { axiosInstance } from "../base/axiosInstance";

export async function obterProfessoresPaginadoApi(page) {
  try {
    const response = await axiosInstance.get(`/professores/paginado/publico?size=4&page=${page}`);

    return response.data;
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
