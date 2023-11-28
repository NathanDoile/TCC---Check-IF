import { gerarRelatorioAlunoApi } from "../../api";
import { toast } from "react-toastify";

export function useGerarRelatorioAluno(){

    async function gerarRelatorioAluno(id, mes, ano){

        try{
            
            const response = await gerarRelatorioAlunoApi(id, Number(mes), Number(ano));

            return response;
        }
        catch(error){
            toast.error(error);
        }

    }

    return { gerarRelatorioAluno }
}