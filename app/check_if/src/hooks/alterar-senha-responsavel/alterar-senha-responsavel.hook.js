import { alterarSenhaResponsavelApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useAlterarSenhaResponsavel(){

    const navigate = useNavigate();

    async function alterarSenhaResponsavel(senhaAtual, novaSenha){

        try{
            await alterarSenhaResponsavelApi(senhaAtual, novaSenha);

            toast.success("Senha alterada com sucesso");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return { alterarSenhaResponsavel }
}