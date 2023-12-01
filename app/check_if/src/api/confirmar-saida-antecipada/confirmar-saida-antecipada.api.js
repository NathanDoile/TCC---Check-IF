import { axiosInstance } from "../base/axiosInstance";

export async function confirmarSaidaAntecipadaApi(id, saiu) {
  try {
    await axiosInstance.put("/saidas-antecipadas/confirmar", {
      id,
      saiu
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
