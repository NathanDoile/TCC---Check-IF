import "./editar-perfil.screen.css";
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { Cabecalho, TituloTelasIniciais, Botao } from "../../component";
import userImg from "../../../assets/images/UserCirculo-marrom.svg";

export function TelaEditarPerfil() {
  const [usuario] = useGlobalUsuario();

  return (
    <>
      <Cabecalho />

      <main className="main-tela-editar-perfil">
        <TituloTelasIniciais>Editar informações do perfil</TituloTelasIniciais>

        <img src={userImg} alt="Usuário" className="icone-user-editar-perfil" />

        <form className="form-editar-perfil">
          <label className="label-editar-perfil">
            Nome:
            <input type="text" className="input-editar" name="nome" />
          </label>

          <label className="label-editar-perfil">
            E-mail:
            <input type="text" className="input-editar" name="email" />
          </label>

          {usuario.permissao === "RESPONSAVEL" ? (
            <label className="label-editar-perfil">
              Celular:
              <input type="text" className="input-editar" name="celular" />
            </label>
          ) : null}

          <Botao cor="laranja">Salvar</Botao>
        </form>
      </main>
    </>
  );
}
