import useGlobalUsuario from "../../context/usuario/usuario.context";
import { useNavigate } from 'react-router-dom';
import { TelaPaginaNaoEncontrada } from "../../ui/screen";

export function PrivateRouteResponsavel ({Screen}){
    
    const [usuario] = useGlobalUsuario();

    const navigate = useNavigate();

    if(usuario){

        if(usuario.permissao === "RESPONSAVEL"){
            return <Screen />
        }
        else{
            return <TelaPaginaNaoEncontrada />
        }
    }
    else{
        navigate("/login");
    }
}