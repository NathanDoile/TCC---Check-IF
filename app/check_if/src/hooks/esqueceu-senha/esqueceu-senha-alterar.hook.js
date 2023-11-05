import { esqueceuSenhaAlterarApi } from '../../api';
import { useNavigate } from "react-router-dom";
import useGlobalUsuarioEsqueceuSenha from "../../context/esqueceu-senha/esqueceu-senha.context";
import { toast } from "react-toastify";

export function useEsqueceuSenhaAlterar() {

    const navigate = useNavigate();

    const [usuario, setUsuario] = useGlobalUsuarioEsqueceuSenha();

    async function esqueceuSenhaAlterar(senha) {
        try {

            await esqueceuSenhaAlterarApi(senha, usuario.email, usuario.token);

            localStorage.clear();

            toast.success("Senha alterada com sucesso.");

            navigate("/login");

        } catch (error) {

            if(error?.message === "Suas tentativas acabaram, solicite outro token para alterar sua senha." || 
                error?.message === "Token expirado, por favor, gere um novo token para alterar sua senha."){

                toast.error(`${error?.message}`)

                navigate("/esqueceu-senha");

                localStorage.clear();
            }
            else if(error?.message === "Token inválido."){

                toast.error(`${error?.message}`)

                navigate("/esqueceu-senha/token");

                setUsuario({"email":usuario.email});
            }
            
        }
    }

    return { esqueceuSenhaAlterar };
}