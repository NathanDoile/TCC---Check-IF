import { axiosInstance } from "../base/axiosInstance";

export async function alterarAdministradorApi(nome, email) {
  try {
    await axiosInstance.put("/administradores", {
      nome,
      email,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
