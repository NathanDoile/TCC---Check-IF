import "./pesquisar-aluno.screen.css";
import { useParams } from "react-router-dom";
import { Cabecalho, Navbar, Aluno } from "../../component";
import lupa from "../../../assets/images/Lupa-marrom.svg";
import { useEffect, useState } from "react";
import { usePesquisarAluno } from "../../../hooks";
import loading from '../../../assets/images/loading.svg';

export function TelaPesquisarAluno() {

  const [carregando, setCarregando] = useState(false);

  const { texto } = useParams();

  const { pesquisarAluno } = usePesquisarAluno();

  const [pesquisa, setPesquisa] = useState(texto);

  const [alunos, setAlunos] = useState([]);

  const [alunosTag, setAlunosTag] = useState([]);

  const [pagina, setPagina] = useState(0);

  const [numeroPaginas, setNumeroPaginas] = useState();

  function handleChange(event){

    const {value} = event.target;

    setPesquisa(value);
  }

  function handleSubmit(event){
    event.preventDefault();

    setPesquisa(pesquisa)
  }

  useEffect(() => {

    async function pesquisar() {

      setCarregando(true);

      const response = await pesquisarAluno(pesquisa, pagina);

      setCarregando(false);

      if(response && response.hasOwnProperty("content")){
        setAlunos(response.content);
        setNumeroPaginas(response.totalPages);
      }
    }

    pesquisar();

  }, [pesquisa, pagina]);

  useEffect(() => {

    setAlunosTag([]);

    alunos.forEach((aluno) => {

      setAlunosTag((oldAlunosTag) => ([...oldAlunosTag,
        <Aluno
          nome={aluno.nome}
          turma={aluno.turma}
          matricula={aluno.matricula}
          dataNascimento={aluno.dataNascimento}
          id={aluno.id}
        />
      ]))
    });

  }, [alunos]);

  return (
    <>
      <Cabecalho />

      <main className="main-pesquisar-aluno">
        <form className="form-pesquisar-aluno-tela" onSubmit={handleSubmit}>

          <label className="label-pesquisar-tela-aluno">
            <input
              type="search"
              name="aluno"
              className="input-pesquisar-aluno-tela"
              onChange={handleChange}
              required
              defaultValue={texto}
            />

            <button className="botao-pesquisar-tela-aluno">
              <img src={lupa} alt="Lupa" className="lupa-icone" />
            </button>
          </label>

          {carregando ? <img src={loading} className="loading-pesquisa" /> : null}

        </form>

        <div className="lista-aluno">
          {alunosTag.length > 0 
          ? alunosTag 
          : <h2>Nenhum aluno encontrado!</h2>}
        </div>

        <Navbar
          paginaAtual={pagina}
          numeroPaginas={numeroPaginas}
          alterarPagina={setPagina}
        />
      </main>
    </>
  );
}
