import "./editar-perfil.screen.css";
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { Cabecalho, TituloTelasIniciais, Botao } from "../../component";
import userImg from "../../../assets/images/UserCirculo-marrom.svg";
import { useAlterarAdministrador, useAlterarResponsavel } from "../../../hooks";
import { useState } from "react";

export function TelaEditarPerfil() {

  const [carregando, setCarregando] = useState(false);

  const [usuario] = useGlobalUsuario();

  const [formInput, setFormInput] = useState({
    nome: usuario.nome,
    email: usuario.email,
    celular: usuario.celular
  })

  const { alterarAdministrador } = useAlterarAdministrador();

  const { alterarResponsavel }= useAlterarResponsavel();

  function handleChange(event){

    const { value, name } = event.target;

    setFormInput((oldFormInput) => ({...oldFormInput, [name]:value}));

  }

  async function handleSubmit(event){

    event.preventDefault();

    if(usuario.permissao === "ADMINISTRADOR"){
      
      setCarregando(true);
      
      await alterarAdministrador(formInput.nome, formInput.email);

      setCarregando(false);

    }
    else{

      setCarregando(true);

      await alterarResponsavel(formInput.nome, formInput.email, formInput.celular);

      setCarregando(false);
    }
  }

  return (
    <>
      <Cabecalho />

      <main className="main-tela-editar-perfil">
        <TituloTelasIniciais>Editar informações do perfil</TituloTelasIniciais>

        <img src={userImg} alt="Usuário" className="icone-user-editar-perfil" />

        <form className="form-editar-perfil" onSubmit={handleSubmit}>
          <label className="label-editar-perfil">
            Nome:
            <input type="text" className="input-editar" name="nome" defaultValue={formInput.nome} onChange={handleChange} />
          </label>

          <label className="label-editar-perfil">
            E-mail:
            <input type="text" className="input-editar" name="email" defaultValue={formInput.email} onChange={handleChange} />
          </label>

          {usuario.permissao === "RESPONSAVEL" ? (
            <label className="label-editar-perfil">
              Celular:
              <input type="text" className="input-editar" name="celular" defaultValue={formInput.celular} onChange={handleChange} />
            </label>
          ) : null}

          <Botao cor="laranja" carregando={carregando}>Salvar</Botao>
        </form>
      </main>
    </>
  );
}
