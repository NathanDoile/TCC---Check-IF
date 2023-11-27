import { obterAlunoApi } from "../../api";
import { toast } from "react-toastify";

export function useObterAluno(){

    async function obterAluno(id){

        try{
            const response = await obterAlunoApi(id);
    
            return response;
        }
        catch(error){
            toast.error(error);
        }

    }

    return { obterAluno }
}