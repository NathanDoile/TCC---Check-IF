import "./alterar-senha-padrao.screen.css";
import {
  Cabecalho,
  TituloTelasIniciais,
  InputSenha,
  Botao,
} from "../../component";
import { useAlterarSenhaAdministrador, useAlterarSenhaResponsavel, useAlterarSenhaPortaria } from "../../../hooks";
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { useState } from "react";
import { toast } from "react-toastify";

export function TelaAlterarSenhaPadrao() {

  const [carregando, setCarregando] = useState(false);

  const [usuario] = useGlobalUsuario();

  const { alterarSenhaAdministrador } = useAlterarSenhaAdministrador();

  const { alterarSenhaResponsavel } = useAlterarSenhaResponsavel();

  const { alterarSenhaPortaria } = useAlterarSenhaPortaria();

  const [formInput, setFormInput] = useState({
    senhaAtual: "",
    novaSenha: "",
    confirmarNovaSenha: ""
  })

  function handleChange(event){

    const { value, name } = event.target;

    setFormInput((oldFormInput) => ({...oldFormInput, [name]:value}));

  }

  async function handleSubmit(event){
    event.preventDefault();

    if(formInput.novaSenha !== formInput.confirmarNovaSenha){
      toast.error("A nova senha e a confirmação devem ser iguais.");
    }
    else{

      if(usuario.permissao === "ADMINISTRADOR"){

        setCarregando(true);

        await alterarSenhaAdministrador(formInput.senhaAtual, formInput.novaSenha);

        setCarregando(false);
      }
      else if(usuario.permissao === "RESPONSAVEL"){

        setCarregando(true);

        await alterarSenhaResponsavel(formInput.senhaAtual, formInput.novaSenha);

        setCarregando(false);
      }
      else{

        setCarregando(true);

        await alterarSenhaPortaria(formInput.senhaAtual, formInput.novaSenha);

        setCarregando(false);
      }
    }
  }

  return (
    <>
      <Cabecalho />

      <main className="main-tela-alterar-senha-padrao">
        <TituloTelasIniciais>Alterar senha</TituloTelasIniciais>

        <form className="form-alterar-senha-padrao" onSubmit={handleSubmit}>
          <InputSenha
            legenda="Senha atual"
            name="senhaAtual"
            handleChange={handleChange}
          />

          <InputSenha
            legenda="Nova senha"
            name="novaSenha"
            handleChange={handleChange}
          />

          <InputSenha
            legenda="Confirmar nova senha"
            name="confirmarNovaSenha"
            handleChange={handleChange}
          />

          <Botao cor="laranja" carregando={carregando}>Alterar</Botao>
        </form>
      </main>
    </>
  );
}
