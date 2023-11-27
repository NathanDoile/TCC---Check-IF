import { alterarSenhaPortariaApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useAlterarSenhaPortaria(){

    const navigate = useNavigate();

    async function alterarSenhaPortaria(senhaAtual, novaSenha){

        try{
            await alterarSenhaPortariaApi(senhaAtual, novaSenha);

            toast.success("Senha alterada com sucesso");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return { alterarSenhaPortaria }
}