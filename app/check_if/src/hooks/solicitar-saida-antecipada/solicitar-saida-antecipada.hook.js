import { solicitarSaidaAntecipadaApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useSolicitarSaidaAntecipada(){

    const navigate = useNavigate();

    async function solicitarSaidaAntecipada(dataAutorizada, horaAutorizada, motivo, idAluno){

        try{
            await solicitarSaidaAntecipadaApi(dataAutorizada, horaAutorizada, motivo, idAluno);

            toast.success("Saída solicitada com sucesso.");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return { solicitarSaidaAntecipada }
}