import { alterarAdministradorApi } from '../../api';
import { toast } from 'react-toastify';

export function useAlterarAdministrador(){

    async function alterarAdministrador(nome, email){

        try{
            await alterarAdministradorApi(nome, email);

            toast.success("Alteração realizada com sucesso!");
        }
        catch(error){
            toast.error(error);
        }
    }

    return {alterarAdministrador}
}