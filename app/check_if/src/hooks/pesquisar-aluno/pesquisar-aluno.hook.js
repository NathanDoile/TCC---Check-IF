import { pesquisarAlunoApi } from "../../api";
import { toast } from "react-toastify";

export function usePesquisarAluno(){

    async function pesquisarAluno(texto){

        try{
            const response = await pesquisarAlunoApi(texto);

            return response;
        }
        catch(error){
            toast.error(error);
        }
    }

    return { pesquisarAluno }
}