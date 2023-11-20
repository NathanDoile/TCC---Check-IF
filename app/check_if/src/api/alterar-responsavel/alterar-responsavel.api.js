import { axiosInstance } from "../base/axiosInstance";

export async function alterarResponsavelApi(nome, email, celular) {
  try {
    await axiosInstance.put("/responsaveis", {
      nome,
      email,
      celular,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
