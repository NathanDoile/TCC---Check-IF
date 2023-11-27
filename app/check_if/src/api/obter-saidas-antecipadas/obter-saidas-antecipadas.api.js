import { axiosInstance } from "../base/axiosInstance";

export async function obterSaidasAntecipadasApi(page) {
  try {
    const response = await axiosInstance.get(`/saidas-antecipadas?page=${page}&size=${8}`);

    return response.data;
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
