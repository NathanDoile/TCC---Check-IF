import { alterarAdministradorApi } from "../../api";
import { toast } from "react-toastify";
import useGlobalUsuario from "../../context/usuario/usuario.context";

export function useAlterarAdministrador() {

  const [usuario, setUsuario] = useGlobalUsuario();

  async function alterarAdministrador(nome, email) {
    try {
      await alterarAdministradorApi(nome, email);

      toast.success("Alteração realizada com sucesso!");
     
      setUsuario({...usuario, nome:nome, email:email});
    } catch (error) {
      toast.error(error);
    }
  }

  return { alterarAdministrador };
}
