import "./adicionar-professor.screen.css";
import { Cabecalho, TituloTelasIniciais, Input, Botao } from "../../component";
import { useCadastrarProfessor } from "../../../hooks";
import { useState } from "react";
import { toast } from "react-toastify";

export function TelaAdicionarProfessor() {

  const [formInput, setFormInput] = useState({
    nome: "",
    email:"",
    siape:"",
    celular:""
  })

  const { cadastrarProfessor } = useCadastrarProfessor();

  function handleChange(event){

    const { value, name } = event.target;

    setFormInput((oldFormInput) => ({...oldFormInput, [name]:value}));
  }

  async function handleSubmit(event){

    event.preventDefault();

    if(formInput.nome === "" || formInput.email === "" || formInput.siape === ""){
      toast.error("Preencha todos os campos obrigatórios.");
    }
    else{
      await cadastrarProfessor(formInput.nome, formInput.email, formInput.siape, formInput.celular);
    }
  }

  return (
    <>
      <Cabecalho />

      <main className="main-adicionar-professor">
        <TituloTelasIniciais>Adicionar professor</TituloTelasIniciais>

        <form className="form-adicionar-professor" onSubmit={handleSubmit}>
          <Input
            legenda="Nome completo"
            isObrigatorio
            name="nome"
            handleChange={handleChange}
            type="text"
            grande
          />

          <Input
            legenda="Siape"
            isObrigatorio
            name="siape"
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

          <Botao cor="laranja">Adicionar</Botao>
        </form>
      </main>
    </>
  );
}
