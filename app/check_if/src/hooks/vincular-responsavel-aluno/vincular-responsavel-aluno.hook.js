import { vincularResponsavelAlunoApi} from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useVincularResponsavelAluno(){

    const navigate = useNavigate();

    async function vincularResponsavelAluno(matricula, email){

        try{
            await vincularResponsavelAlunoApi(matricula, email);

            toast.success("Responsável vinculado ao aluno com sucesso!");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return { vincularResponsavelAluno }
}