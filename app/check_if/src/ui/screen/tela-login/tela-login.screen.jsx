import "./tela-login.screen.css";
import {
  Borda,
  LogoInicial,
  TituloTelasIniciais,
  Botao,
  InputEspecifico,
} from "../../component";
import { useState } from "react";
import usuario from "../../../assets/images/User-marrom.svg";
import cadeado from "../../../assets/images/Cadeado-marrom.svg";
import { useLogin } from "../../../hooks";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function TelaLogin() {

  const [carregando, setCarregando] = useState(false);

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

      setCarregando(true);

      await fazerLogin(formInput.email, formInput.senha);

      setCarregando(false);
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
          <InputEspecifico
            imagem={usuario}
            alt="usuário"
            type="email"
            name="email"
            placeholder="Usuário"
            isRequired={true}
            handleChange={handleChange}
          />

          <span className="input-senha">
            <InputEspecifico
              imagem={cadeado}
              alt="cadeado"
              type="password"
              name="senha"
              placeholder="Senha"
              isRequired={true}
              handleChange={handleChange}
            />

            <p className="esqueceu-senha" onClick={esqueceuSenha}>
              Esqueceu sua senha?
            </p>
          </span>

          <Botao cor="amarelo" carregando={carregando}>Logar</Botao>
        </form>
      </main>

      <Borda posicao={"inferior"} />
    </>
  );
}
