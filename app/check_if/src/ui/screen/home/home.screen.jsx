import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { TelaHomeAdministrador } from "./home-administrador/home-administrador.screen";
import { TelaHomePortaria } from "./home-portaria/home-portaria.screen";
import { TelaHomeResponsavel } from "./home-responsavel/home-responsavel.screen";

export function TelaHome() {
  const [usuario] = useGlobalUsuario();

  return (
    <>
      {usuario.permissao === "ADMINISTRADOR" ? <TelaHomeAdministrador /> : null}
      {usuario.permissao === "PORTARIA" ? <TelaHomePortaria /> : null}
      {usuario.permissao === "RESPONSAVEL" ? <TelaHomeResponsavel /> : null}
    </>
  );
}
