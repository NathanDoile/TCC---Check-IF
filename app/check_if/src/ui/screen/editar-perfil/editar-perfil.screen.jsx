import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { TelaEditarPerfilAdministrador } from "./editar-perfil-administrador/editar-perfil-administrador.screen";

export function TelaEditarPerfil(){

    const [usuario] = useGlobalUsuario();

    return(
        <>
            {usuario.permissao === "ADMINISTRADOR" ? <TelaEditarPerfilAdministrador /> : null}
        </>
    )
}