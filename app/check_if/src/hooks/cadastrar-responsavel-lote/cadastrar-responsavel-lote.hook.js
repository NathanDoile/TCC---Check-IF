import { cadastrarResponsavelLoteApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useCadastrarResponsavelLote(){

    const navigate = useNavigate();

    async function cadastrarResponsavelLote(arquivo){

        try{
            await cadastrarResponsavelLoteApi(arquivo);

            toast.success("Cadastro realizado com sucesso.");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }

    }

    return { cadastrarResponsavelLote }
}