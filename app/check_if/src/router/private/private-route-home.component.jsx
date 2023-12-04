import useGlobalUsuario from "../../context/usuario/usuario.context";
import { useNavigate } from 'react-router-dom';
import { TelaHomeAdministrador, TelaHomeResponsavel, TelaHomePortaria } from "../../ui/screen";

export function PrivateRouteHome (){
    
    const [usuario] = useGlobalUsuario();

    const navigate = useNavigate();

    if(usuario){

        if(usuario.permissao === "ADMINISTRADOR"){
            return <TelaHomeAdministrador />
        }
        else if(usuario.permissao === "RESPONSAVEL"){
            return <TelaHomeResponsavel />
        }
        else{
            return <TelaHomePortaria />
        }
    }
    else{
        navigate("/login");
    }
}