import useGlobalUsuario from "../../context/usuario/usuario.context";
import { useNavigate } from 'react-router-dom';
import { TelaPaginaNaoEncontrada } from "../../ui/screen";

export function PrivateRouteAdministrador({Screen}){
    
    const [usuario] = useGlobalUsuario();

    const navigate = useNavigate();

    if(usuario){

        if(usuario.permissao === "ADMINISTRADOR"){
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