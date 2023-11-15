import { cadastrarResponsavelApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useCadastrarResponsavel() {
  const navigate = useNavigate();

  async function cadastrarResponsavel(nome, email, celular, matricula) {
    try {
      await cadastrarResponsavelApi(nome, email, celular, matricula);

      toast.success("Cadastro realizado com sucesso!");

      navigate("/home");
    } catch (error) {
      toast.error(error);
    }
  }

  return { cadastrarResponsavel };
}
