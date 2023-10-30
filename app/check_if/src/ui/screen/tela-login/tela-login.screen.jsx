import "./tela-login.screen.css";
import {
  Borda,
  LogoInicial,
  TituloTelasIniciais,
  Botao,
} from "../../component";
import { useState } from "react";
import usuario from "../../../assets/images/User-marrom.svg";
import cadeado from "../../../assets/images/Cadeado-marrom.svg";
import { useLogin } from "../../../hooks";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function TelaLogin() {
  const { fazerLogin } = useLogin();
  const navigate = useNavigate();

  const [formInput, setFormInput] = useState({
    email: "",
    senha: "",
  });

  function handleChange(event) {
    const { name, value } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
  }

  async function onSubmit(event) {
    event.preventDefault();

    if (formInput.email === "" || formInput.senha === "") {
      toast.error("Preencha todos os campos!");
    } else {
      await fazerLogin(formInput.email, formInput.senha);
    }
  }

  function esqueceuSenha() {
    navigate("/esqueceu-senha");
  }

  return (
    <>
      <Borda posicao={"superior"} />

      <main className="main-tela-login">
        <LogoInicial />

        <TituloTelasIniciais>Faça seu login</TituloTelasIniciais>

        <form className="form-login" onSubmit={onSubmit}>
          <div className="input-login">
            <img src={usuario} alt="usuário" className="imagem-input" />
            <input
              type="text"
              name="email"
              placeholder="Usuário"
              className="texto-input"
              required
              onChange={handleChange}
            />
          </div>

          <span className="input-senha">
            <div className="input-login">
              <img src={cadeado} alt="usuário" className="imagem-input" />
              <input
                type="password"
                name="senha"
                placeholder="Senha"
                className="texto-input"
                required
                onChange={handleChange}
              />
            </div>
            <p className="esqueceu-senha" onClick={esqueceuSenha}>
              Esqueceu sua senha?
            </p>
          </span>

          <Botao cor="amarelo">Logar</Botao>
        </form>
      </main>

      <Borda posicao={"inferior"} />
    </>
  );
}
