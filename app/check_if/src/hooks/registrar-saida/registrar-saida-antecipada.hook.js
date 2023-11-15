import { registrarSaidaAntecipadaApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useRegistrarSaidaAntecipada(){

    const navigate = useNavigate();

    async function registrarSaidaAntecipada(nomeResponsavel, grauParentesco, motivo, matriculaAluno){

        try{
            await registrarSaidaAntecipadaApi(nomeResponsavel, grauParentesco, motivo, matriculaAluno);

            toast.success("Saída antecipada registrada com sucesso!")

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return {registrarSaidaAntecipada}
}