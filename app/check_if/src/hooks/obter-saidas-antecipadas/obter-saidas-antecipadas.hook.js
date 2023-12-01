import { obterSaidasAntecipadasApi } from "../../api";
import { toast } from "react-toastify";

export function useObterSaidasAntecipadas(){

    async function obterSaidasAntecipadas(page){

        try{
            const response = await obterSaidasAntecipadasApi(page);
            
            return response;
        }
        catch(error){
            toast.error(error);
        }
    }

    return { obterSaidasAntecipadas }
}