import { registrarChegadaAtrasadaCrachaApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useRegistrarChegadaAtrasadaCracha() {
  const navigate = useNavigate();

  async function registrarChegadaAtrasadaCracha(
    motivo,
    disciplina,
    matriculaAluno,
    idProfessor
  ) {
    try {
      await registrarChegadaAtrasadaCrachaApi(
        motivo,
        disciplina,
        matriculaAluno,
        idProfessor
      );

      toast.success("Registro realizado com sucesso!");

      navigate("/");
    } catch (error) {
      toast.error(error);
    }
  }

  return { registrarChegadaAtrasadaCracha };
}
