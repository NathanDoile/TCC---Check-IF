import { toast } from "react-toastify";
import { esqueceuSenhaApi } from "../../api";
import { useNavigate } from "react-router-dom";
import useGlobalUsuarioEsqueceuSenha from "../../context/esqueceu-senha/esqueceu-senha.context";

export function useEsqueceuSenha() {

    const navigate = useNavigate();

    const [, setUsuario] = useGlobalUsuarioEsqueceuSenha();

    async function esqueceuSenha(email) {
        try {

            await esqueceuSenhaApi(email);

            setUsuario({email});

            navigate("/esqueceu-senha/token");

        } catch (error) {
            toast.error(`${error}`);
        }
    }

    return { esqueceuSenha };
}