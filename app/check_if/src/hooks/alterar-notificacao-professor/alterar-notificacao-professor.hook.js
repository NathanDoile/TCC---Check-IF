import { alterarNotificacaoProfessorApi } from "../../api";
import { toast } from "react-toastify";

export function useAlterarNotificacaoProfessor(){

    async function alterarNotificacaoProfessor(id, tipoNotificacao){

        try{
            await alterarNotificacaoProfessorApi(id, tipoNotificacao);

            toast.success("Alteração realizada com sucesso!");
        }
        catch(error){
            toast.error(error);        }
    }

    return {alterarNotificacaoProfessor}
}