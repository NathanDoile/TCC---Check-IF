import { alterarSenhaAdministradorApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useAlterarSenhaAdministrador(){

    const navigate = useNavigate();

    async function alterarSenhaAdministrador(senhaAtual, novaSenha){

        try{
            await alterarSenhaAdministradorApi(senhaAtual, novaSenha);

            toast.success("Senha alterada com sucesso");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return { alterarSenhaAdministrador }
}