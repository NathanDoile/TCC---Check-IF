import { confirmarSaidaAntecipadaApi } from "../../api";
import { toast } from "react-toastify";

export function useConfirmarSaidaAntecipada(){

    async function confirmarSaidaAntecipada(id, saiu){

        try{
            await confirmarSaidaAntecipadaApi(id, saiu);

            toast.success("Saída antecipada resolvida.")
        }
        catch(error){
            toast.error(error);
        }

    }

    return { confirmarSaidaAntecipada }
}