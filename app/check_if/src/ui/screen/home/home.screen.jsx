import "./home.screen.css";
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { TelaHomeAdministrador } from "./home-administrador/home-administrador.screen";

export function TelaHome() {

  const [usuario] = useGlobalUsuario();

  return(
    <>
      {usuario.permissao === "ADMINISTRADOR" ? <TelaHomeAdministrador /> : null}
    </>
  )
  
}
