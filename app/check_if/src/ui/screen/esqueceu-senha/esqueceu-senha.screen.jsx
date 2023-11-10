import "./esqueceu-senha.screen.css";
import { Borda, LogoInicial, TituloTelasIniciais, Botao, InputEspecifico } from '../../component';
import envelope from '../../../assets/images/Email-marrom.svg';
import { toast } from "react-toastify";
import { useState } from "react";
import { useEsqueceuSenha } from '../../../hooks';

export function TelaEsqueceuSenha() {

  const [email, setEmail] = useState("");

  const { esqueceuSenha } = useEsqueceuSenha();

  function handleChange(event) {
    const { value } = event.target;

    setEmail(value);
  }

  async function onSubmit(event) {
    event.preventDefault();

    if (email === "") {
      toast.error("Preencha todos os campos!");
    } else {
      
      await esqueceuSenha(email);
    }
  }

  return (
    <section className="section-tela-esqueceu-senha">
      <Borda posicao="superior" />

      <main className="tela-esqueceu-senha">

        <LogoInicial />

        <TituloTelasIniciais>Informe o endereço de e-mail</TituloTelasIniciais>

        <h2 className="legenda-tela-esqueceu-senha">Digite aqui o endereço de e-mail da conta que você deseja recuperar a senha</h2>

        <form className="form-esqueceu-senha" onSubmit={onSubmit}>

          <InputEspecifico imagem={envelope} alt="envelope" type="email" name="email" placeholder="E-mail"
            isRequired={true} handleChange={handleChange} />

          <Botao cor="verde">Avançar</Botao>

        </form>

      </main>

      <Borda posicao="inferior" />
    </section>
  );
}
