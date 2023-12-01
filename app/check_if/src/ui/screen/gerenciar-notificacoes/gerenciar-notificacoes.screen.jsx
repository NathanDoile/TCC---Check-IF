import "./gerenciar-notificacoes.screen.css";
import { Cabecalho, TituloTelasIniciais, Notificacao } from "../../component";
import useGlobalUsuario from "../../../context/usuario/usuario.context";
import { useObterAlunoPorResponsavel } from "../../../hooks";
import { useState, useEffect } from "react";

export function TelaGerenciarNotificacoes() {

  const [usuario] = useGlobalUsuario();

  const [alunos, setAlunos] = useState([]);

  const [alunosTag, setAlunosTag] = useState([]);

  const { obterAlunosPorResponsavel } = useObterAlunoPorResponsavel();

  useEffect(() => {

    async function obter() {

      const response = await obterAlunosPorResponsavel();

      setAlunos(response);
    }

    obter();
  }, []);

  useEffect(() => {

    setAlunosTag([]);

    alunos.forEach(aluno => {

      setAlunosTag((oldAlunosTag) => [...oldAlunosTag,
      <Notificacao
        id={aluno.id}
        nome={aluno.nome}
        turma={aluno.turma}
        matricula={aluno.matricula}
        numero={usuario.celular}
        email={usuario.email}
        notificacaoEmail={aluno.notificacaoEmail}
        notificacaoNumero={aluno.notificacaoWhatsapp}
      />
      ])
    })
  }, [alunos]);

  return (
    <>
      <Cabecalho />

      <main className="main-gerenciar-notificacoes">
        <TituloTelasIniciais>Gerenciar notificações</TituloTelasIniciais>

        <div className="container-notificacoes-responsavel">
          {alunosTag}
        </div>
      </main>
    </>
  );
}
