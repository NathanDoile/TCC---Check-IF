import { axiosInstance } from "../base/axiosInstance";

export async function obterProfessoresApi(page) {
  try {
    const response = await axiosInstance.get(`/professores/publico?size=4&page=${page}`);

    return response.data;
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
