import { verSaidasAntecipadasApi } from "../../api";
import { toast } from 'react-toastify';

export function useVerSaidasAntecipadas(){

    async function verSaidasAntecipadas(page, data){
        try{
            const response = await verSaidasAntecipadasApi(page, data);

            return response;
        }
        catch(error){
            toast.error(error);
        }
    }

    return { verSaidasAntecipadas };
}