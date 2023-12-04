import "./cadastrar-responsavel.screen.css";
import { Cabecalho, TituloTelasIniciais, Input, Botao } from "../../component";
import { useState } from "react";
import { toast } from "react-toastify";
import { useCadastrarResponsavel } from "../../../hooks";

export function TelaCadastrarResponsavel() {

  const [carregando, setCarregando] = useState(false);

  const { cadastrarResponsavel } = useCadastrarResponsavel();

  const [formInput, setFormInput] = useState({
    nome: "",
    email: "",
    celular: "",
    aluno: "",
  });

  function handleChange(event) {
    const { name, value } = event.target;

    setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
  }

  async function cadastrar(event) {
    event.preventDefault();

    if (
      formInput.nome === "" ||
      formInput.email === "" ||
      formInput.aluno === ""
    ) {
      toast.error("Preencha todos os campos obrigatórios!");
    } else {

      setCarregando(true);

      await cadastrarResponsavel(
        formInput.nome,
        formInput.email,
        formInput.celular,
        formInput.aluno
      );

      setCarregando(false);
    }
  }

  return (
    <>
      <Cabecalho />

      <main className="main-cadastrar-responsavel">
        <TituloTelasIniciais>Cadastrar responsável</TituloTelasIniciais>

        <form className="form-cadastrar-responsavel" onSubmit={cadastrar}>
          <Input
            legenda="Nome completo"
            isObrigatorio
            name="nome"
            handleChange={handleChange}
            type="text"
            grande
          />

          <Input
            legenda="E-mail"
            isObrigatorio
            name="email"
            handleChange={handleChange}
            type="text"
            grande
          />

          <Input
            legenda="Celular"
            name="celular"
            handleChange={handleChange}
            type="text"
            grande
          />

          <Input
            legenda="Aluno"
            isObrigatorio
            name="aluno"
            handleChange={handleChange}
            type="text"
            grande
          />

          <Botao cor="laranja" carregando={carregando}>Cadastrar</Botao>
        </form>
      </main>
    </>
  );
}
