import { axiosInstance } from "../base/axiosInstance";

export async function cadastrarResponsavelApi(nome, email, celular, matricula) {
  try {
    await axiosInstance.post("/responsaveis", {
      nome,
      email,
      celular,
      matricula,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
