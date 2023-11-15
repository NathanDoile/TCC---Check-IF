import { cadastrarAdministradorApi } from "../../api";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

export function useCadastrarAdministrador(){

    const navigate = useNavigate();

    async function cadastrarAdministrador(nome, siape, email){

        try{
            await cadastrarAdministradorApi(nome, siape, email);

            toast.success("Cadastro realizado com sucesso!");

            navigate("/home");
        }
        catch(error){
            toast.error(error);
        }
    }

    return {cadastrarAdministrador}
}