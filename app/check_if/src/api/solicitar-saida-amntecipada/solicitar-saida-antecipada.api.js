import { axiosInstance } from "../base/axiosInstance";

export async function solicitarSaidaAntecipada(
  dataAutorizada,
  horaAutorizada,
  motivo,
  idAluno
) {
  try {
    await axiosInstance.post("/saidas-antecipadas/solicitar", {
      dataAutorizada,
      horaAutorizada,
      motivo,
      idAluno,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
