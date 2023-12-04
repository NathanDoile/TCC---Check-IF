import "./vincular-aluno-responsavel.screen.css";
import { Cabecalho, TituloTelasIniciais, Input, Botao } from "../../component";
import ajudaImg from "../../../assets/images/Ajuda.svg";
import { useState } from "react";
import { useVincularResponsavelAluno } from "../../../hooks";
import { toast } from 'react-toastify';

export function TelaVincularAlunoResponsavel() {

  const [carregando, setCarregando] = useState(false);

  const [ajuda, setAjuda] = useState(false);

  const [formInput, setFormInput] = useState({
    matricula: "",
    email: ""
  })

  const { vincularResponsavelAluno } = useVincularResponsavelAluno();

  function handleChange(event){

    const { name, value} = event.target;

    setFormInput((oldFormInput) => ({...oldFormInput, [name]:value}));
  }

  async function handleSubmit(event){
    
    event.preventDefault();

    if(formInput.email === "" || formInput.matricula === ""){
      toast.error("Preencha todos os campos.");
    }
    else{

      setCarregando(true);

      await vincularResponsavelAluno(formInput.matricula, formInput.email);

      setCarregando(false);

    }
  }

  return (
    <>
      <Cabecalho />

      <main className="main-tela-vincular">
        <TituloTelasIniciais>Vincular aluno ao responsável</TituloTelasIniciais>

        <form className="form-vincular" onSubmit={handleSubmit}>
          <Input
            legenda="Matricula do aluno"
            isObrigatorio
            name="matricula"
            handleChange={handleChange}
            type="text"
            grande
          />

          <Input
            legenda="E-mail do responsável"
            isObrigatorio
            name="email"
            handleChange={handleChange}
            type="email"
            grande
          />

          <Botao cor="laranja" carregando={carregando}>Vincular</Botao>
        </form>

        <img
          className="ajuda-arquivo"
          src={ajudaImg}
          alt="Ajuda"
          onClick={() => {
            setAjuda(!ajuda);
          }}
        />

        {ajuda ? (
          <div className="ajuda-texto-vincular">
            Para fazer uso dessa ferramenta, é necessário que tanto o
            responsável quanto o aluno estejam devidamente registrados no
            sistema. O cadastro pode ser realizado através do upload de um
            arquivo XLS (cadastro em lote) para o cadastro do responsável ou
            através do cadastro individual do responsável.
          </div>
        ) : null}
      </main>
    </>
  );
}
