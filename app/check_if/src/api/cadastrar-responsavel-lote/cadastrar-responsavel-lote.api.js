import { axiosInstance } from "../base/axiosInstance";

export async function cadastrarResponsavelLote(arquivo) {
  try {
    await axiosInstance.post("/responsaveis/lote", {
      arquivo,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
