import { cadastrarProfessorApi } from "../../api";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom"; 

export function useCadastrarProfessor(){

    const navigate = useNavigate();

    async function cadastrarProfessor(nome, email, siape, celular){

        try{
            await cadastrarProfessorApi(nome, email, siape, celular);

            toast.success("Cadastro realizado com sucesso!");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return { cadastrarProfessor }
}