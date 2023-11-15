import { axiosInstance } from "../base/axiosInstance";

export async function obterChegadasAtrasadasApi(data, page) {
  try {
    const response = await axiosInstance.get(
      `/chegadas-atrasadas/${data}/obter?page=${page}&size=${8}`
    );

    return response;
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
