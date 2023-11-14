import { obterChegadasAtrasadasApi } from "../../api";
import { toast } from "react-toastify";

export function useObterChegadasAtrasadas(){

    async function obterChegadasAtrasadas(data, page) {
        try {

            const response = await obterChegadasAtrasadasApi(data, page);

            return response.data;
        } 
        catch (error) {
            toast.error(error);
        }
    }

    return { obterChegadasAtrasadas };
}