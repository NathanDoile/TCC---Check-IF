import "./vincular-aluno-responsavel.screen.css";
import { Cabecalho, TituloTelasIniciais, Input, Botao } from "../../component";
import ajudaImg from "../../../assets/images/Ajuda.svg";
import { useState } from "react";

export function TelaVincularAlunoResponsavel() {
  const [ajuda, setAjuda] = useState(false);

  return (
    <>
      <Cabecalho />

      <main className="main-tela-vincular">
        <TituloTelasIniciais>Vincular aluno ao responsável</TituloTelasIniciais>

        <form className="form-vincular">
          <Input
            legenda="Matricula do aluno"
            isObrigatorio
            name="matricula"
            handleChange={() => {}}
            type="text"
            grande
          />

          <Input
            legenda="E-mail do responsável"
            isObrigatorio
            name="email"
            handleChange={() => {}}
            type="email"
            grande
          />

          <Botao>Vincular</Botao>
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
