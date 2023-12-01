import { obterAlunosPorResponsavelApi } from "../../api";
import { toast } from "react-toastify";

export function useObterAlunoPorResponsavel(){

    async function obterAlunosPorResponsavel(){

        try{

            const response = await obterAlunosPorResponsavelApi();

            return response;
        }
        catch(error){
            toast.error(error);
        }
    }

    return { obterAlunosPorResponsavel }
}