import { registrarChegadaManualApi } from '../../api';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

export function useRegistrarChegadaManual(){

    const navigate = useNavigate();

    async function registrarChegadaManual(motivo, disciplina, matriculaAluno, idProfessor){

        try{
            await registrarChegadaManualApi(motivo, disciplina, matriculaAluno, idProfessor);

            toast.success("Chegada atrasada registrada com sucesso!");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return {registrarChegadaManual}
}