import { axiosInstance } from "../base/axiosInstance";

export async function cadastrarResponsavelLoteApi(arquivo) {
  
  try {
    await axiosInstance.post("/responsaveis/lote", {
      arquivo,
    }, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
