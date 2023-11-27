import "./cadastrar-responsavel-lote.screen.css";
import {
  Cabecalho,
  TituloTelasIniciais,
  InputEspecifico,
  Botao,
} from "../../component";
import nuvem from "../../../assets/images/Upload-marrom.svg";
import ajudaImg from "../../../assets/images/Ajuda.svg";
import { useState } from "react";
import { useCadastrarResponsavelLote } from '../../../hooks';

export function TelaCadastrarResponsavelLote() {

  const [arquivo, setArquivo] = useState();

  const [nomeFile, setNomeFile] = useState("");

  const [ajuda, setAjuda] = useState(false);

  const { cadastrarResponsavelLote } = useCadastrarResponsavelLote();

  function handleChange(event) {
    const nomeArquivo = event.target.files[0].name;

    setNomeFile(nomeArquivo);

    setArquivo(event.target.files[0]);
  }

  async function handleSubmit(event){
    event.preventDefault();
    
    await cadastrarResponsavelLote(arquivo);
  }

  return (
    <>
      <Cabecalho />

      <main className="main-cadastrar-responsavel-lote">
        <TituloTelasIniciais>
          Cadastrar responsáveis em lote
        </TituloTelasIniciais>

        <form className="form-cadastrar-arquivo" onSubmit={handleSubmit}>
          <label className="label-file-xslx">
            Aceita apenas arquivos XLS ou XLSX
          </label>

          <InputEspecifico
            imagem={nuvem}
            alt="Upload"
            type="file"
            name="arquivo"
            placeholder="Escolha um arquivo"
            isRequired={true}
            handleChange={handleChange}
            file={nomeFile}
          />

          <Botao>Cadastrar</Botao>
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
          <div className="ajuda-texto">
            Este documento é produzido pelo setor CORAC através do SUAP e deve
            conter as seguintes colunas, seguindo a ordem:
            <br />
            <b>
              “NomeAluno”
              <br />
              “Matricula”
              <br />
              “Turma” <br />
              “DataNascimento” <br />
              “NomeResponsavel” <br />
              “EmailResponsavel” <br />
              “CelularResponsavel”
              <br />
            </b>
            <span className="ajuda-texto-atencao">Atenção:</span> se o
            responsável não possuir um e-mail registrado, o usuário
            correspondente não será criado, mas o aluno será adicionado no
            sistema. Nesse caso, ele deverá ser criado posteriormente de forma
            individual na seção 'Cadastrar Responsável'.
          </div>
        ) : null}
      </main>
    </>
  );
}
