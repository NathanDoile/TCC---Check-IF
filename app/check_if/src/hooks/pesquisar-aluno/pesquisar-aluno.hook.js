import { pesquisarAlunoApi } from "../../api";
import { toast } from "react-toastify";

export function usePesquisarAluno(){

    async function pesquisarAluno(texto, page){

        try{
            const response = await pesquisarAlunoApi(texto, page);
            
            return response;
        }
        catch(error){
            if(error.status !== 404){
                toast.error(error);
            }
        }
    }

    return { pesquisarAluno }
}