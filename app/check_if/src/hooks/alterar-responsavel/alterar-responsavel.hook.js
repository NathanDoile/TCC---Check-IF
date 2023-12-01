import { alterarResponsavelApi } from "../../api";
import { toast } from "react-toastify";
import useGlobalUsuario from "../../context/usuario/usuario.context";

export function useAlterarResponsavel() {

  const [usuario, setUsuario] = useGlobalUsuario();

  async function alterarResponsavel(nome, email, celular) {
    try {
      await alterarResponsavelApi(nome, email, celular);

      toast.success("Alteração realizada com sucesso!");

      setUsuario({...usuario, nome:nome, email:email, celular:celular});

    } catch (error) {
      toast.error(error);
    }
  }

  return { alterarResponsavel };
}
