import { confirmarSaidaAntecipadaApi } from "../../api";
import { toast } from "react-toastify";

export function useConfirmarSaidaAntecipada(){

    async function confirmarSaidaAntecipada(id, saiu){

        try{
            await confirmarSaidaAntecipadaApi(id, saiu);
        }
        catch(error){
            toast.error(error);
        }

    }

    return { confirmarSaidaAntecipada }
}