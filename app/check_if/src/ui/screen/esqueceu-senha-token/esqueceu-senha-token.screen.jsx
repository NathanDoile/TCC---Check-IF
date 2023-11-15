import "./esqueceu-senha-token.screen.css";
import {
  Borda,
  LogoInicial,
  TituloTelasIniciais,
  Botao,
  InputEspecifico,
} from "../../component";
import verificacao from "../../../assets/images/Verificacao-marrom.svg";
import { toast } from "react-toastify";
import { useState } from "react";
import useGlobalUsuarioEsqueceuSenha from "../../../context/esqueceu-senha/esqueceu-senha.context";
import { useNavigate } from "react-router-dom";
import { useEsqueceuSenha } from "../../../hooks";

export function TelaEsqueceuSenhaToken() {
  const [token, setToken] = useState("");

  const [usuario, setUsuario] = useGlobalUsuarioEsqueceuSenha();

  const navigate = useNavigate();

  const { esqueceuSenha } = useEsqueceuSenha();

  function handleChange(event) {
    const { value } = event.target;

    setToken(value);
  }

  function onSubmit(event) {
    event.preventDefault();

    if (token === "") {
      toast.error("Preencha todos os campos!");
    } else {
      setUsuario({ ...usuario, token });

      navigate("/esqueceu-senha/alterar");
    }
  }

  async function reenviarCodigo() {
    await esqueceuSenha(usuario.email);
  }

  return (
    <section className="section-tela-esqueceu-senha-token">
      <Borda posicao="superior" />

      <main className="tela-esqueceu-senha-token">
        <LogoInicial />

        <TituloTelasIniciais>
          Digite o código de verificação
        </TituloTelasIniciais>

        <span>
          <h2 className="legenda-tela-esqueceu-senha-token">
            Código de verificação enviado para o e-mail {usuario?.email}
          </h2>
          <h3 className="legenda-reenviar-codigo" onClick={reenviarCodigo}>
            Clique aqui para reenviar o código
          </h3>
        </span>

        <form className="form-esqueceu-senha" onSubmit={onSubmit}>
          <InputEspecifico
            imagem={verificacao}
            alt="Verificação"
            type="text"
            name="token"
            placeholder="Código de verificação"
            isRequired={true}
            handleChange={handleChange}
          />

          <Botao cor="verde">Avançar</Botao>
        </form>
      </main>

      <Borda posicao="inferior" />
    </section>
  );
}
