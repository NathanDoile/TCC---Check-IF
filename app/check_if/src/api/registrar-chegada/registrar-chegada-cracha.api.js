import { axiosInstance } from "../base/axiosInstance";

export async function registrarChegadaAtrasadaCrachaApi(
  motivo,
  disciplina,
  matriculaAluno,
  idProfessor
) {
  try {
    await axiosInstance.post("/chegadas-atrasadas/registrar/cracha/publico", {
      motivo,
      disciplina,
      matriculaAluno,
      idProfessor,
    });
  } catch (error) {
    throw error?.response?.data?.message;
  }
}
