import useGlobalUsuario from "../../context/usuario/usuario.context";
import { useNavigate } from 'react-router-dom';

export function PrivateRouteLogado({Screen}){
    
    const [usuario] = useGlobalUsuario();

    const navigate = useNavigate();

    if(usuario){

        return <Screen />
    }
    else{
        navigate("/login");
    }
}