import { toast } from "react-toastify";
import { esqueceuSenhaApi } from "../../api";
import { useNavigate } from "react-router-dom";

export function useEsqueceuSenha() {

    const navigate = useNavigate();

    async function esqueceuSenha(email) {
        try {

            await esqueceuSenhaApi(email);

            navigate("/esqueceu-senha/alterar");

        } catch (error) {
            toast.error(`${error}`);
            throw new Error();
        }
    }

    return { esqueceuSenha };
}