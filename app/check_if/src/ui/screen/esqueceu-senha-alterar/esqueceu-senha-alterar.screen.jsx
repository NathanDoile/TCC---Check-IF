import "./esqueceu-senha-alterar.screen.css";
import {
  Borda,
  LogoInicial,
  TituloTelasIniciais,
  Botao,
} from "../../component";
import olhoAberto from "../../../assets/images/OlhoAberto-marrom.svg";
import olhoFechado from "../../../assets/images/OlhoFechado-marrom.svg";
import { toast } from "react-toastify";
import { useState } from "react";
import { useEsqueceuSenhaAlterar } from "../../../hooks";

export function TelaEsqueceuSenhaAlterar() {
  const [formInput, setFormInput] = useState({
    senha: "",
    confirmarSenha: "",
  });

  const [senhas, setSenhas] = useState({
    senha: false,
    confirmarSenha: false,
  });

  const { esqueceuSenhaAlterar } = useEsqueceuSenhaAlterar();

  function handleChange(event) {
    const { name, value } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
  }

  function mostrarSenha(event) {
    const name = event.target.attributes.id.value;

    setSenhas((oldSenhas) => ({ ...oldSenhas, [name]: !senhas[name] }));
  }

  async function onSubmit(event) {
    event.preventDefault();

    if (formInput.senha === "" || formInput.confirmarSenha === "") {
      toast.error("Preencha todos os campos!");
    } else if (formInput.senha !== formInput.confirmarSenha) {
      toast.error("As senhas devem ser iguais!");
    } else {
      await esqueceuSenhaAlterar(formInput.senha);
    }
  }

  return (
    <section className="section-tela-esqueceu-senha-alterar">
      <Borda posicao="superior" />

      <main className="tela-esqueceu-senha-alterar">
        <LogoInicial />

        <TituloTelasIniciais>Escolha uma nova senha</TituloTelasIniciais>

        <form className="form-alterar-senha" onSubmit={onSubmit}>
          <span>
            Nova senha:
            <div className="input-senha-alterar">
              <input
                type={senhas.senha ? "text" : "password"}
                name="senha"
                className="texto-input"
                required
                onChange={handleChange}
                autoComplete="off"
              />
              <img
                onClick={mostrarSenha}
                src={senhas.senha ? olhoAberto : olhoFechado}
                alt={"Olho"}
                className="imagem-input"
                id="senha"
              />
            </div>
          </span>

          <span>
            Confirmar nova senha:
            <div className="input-senha-alterar">
              <input
                type={senhas.confirmarSenha ? "text" : "password"}
                name="confirmarSenha"
                className="texto-input"
                required
                onChange={handleChange}
                autoComplete="off"
              />
              <img
                onClick={mostrarSenha}
                src={senhas.confirmarSenha ? olhoAberto : olhoFechado}
                alt={"Olho"}
                className="imagem-input"
                id="confirmarSenha"
              />
            </div>
          </span>

          <Botao cor={"laranja"}>Alterar</Botao>
        </form>
      </main>

      <Borda posicao="inferior" />
    </section>
  );
}
