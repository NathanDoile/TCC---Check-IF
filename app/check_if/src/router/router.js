import {
  TelaInicial,
  TelaLogin,
  TelaRegistrarChegadaAluno,
  TelaEsqueceuSenha,
  TelaHome,
  TelaEsqueceuSenhaToken,
  TelaEsqueceuSenhaAlterar,
  TelaRegistrarChegadaAdministrador,
  TelaRegistrarSaidaAntecipada,
  TelaCadastrarAdministrador,
  TelaCadastrarResponsavel,
  TelaCadastrarResponsavelLote,
  TelaPesquisarAluno,
  TelaPerfilAluno,
  TelaGerenciarProfessores,
  TelaEditarPerfil,
  TelaAdicionarProfessor,
  TelaVincularAlunoResponsavel
} from "../ui/screen/";

const { createBrowserRouter } = require("react-router-dom");

export const router = createBrowserRouter([
  {
    path: "/",
    element: <TelaInicial />,
  },
  {
    path: "/login",
    element: <TelaLogin />,
  },
  {
    path: "/registrar-chegada",
    element: <TelaRegistrarChegadaAluno />,
  },
  {
    path: "/esqueceu-senha",
    element: <TelaEsqueceuSenha />,
  },
  {
    path: "/home",
    element: <TelaHome />,
  },
  {
    path: "/esqueceu-senha/token",
    element: <TelaEsqueceuSenhaToken />,
  },
  {
    path: "/esqueceu-senha/alterar",
    element: <TelaEsqueceuSenhaAlterar />,
  },
  {
    path: "/administrador/registrar-chegada-atrasada",
    element: <TelaRegistrarChegadaAdministrador />,
  },
  {
    path: "/registrar-saida-antecipada",
    element: <TelaRegistrarSaidaAntecipada />,
  },
  {
    path: "/cadastrar-administrador",
    element: <TelaCadastrarAdministrador />,
  },
  {
    path: "/cadastrar-responsavel",
    element: <TelaCadastrarResponsavel />,
  },
  {
    path: "/cadastrar-responsavel/lote",
    element: <TelaCadastrarResponsavelLote />,
  },
  {
    path: "/pesquisar/:texto",
    element: <TelaPesquisarAluno />,
  },
  {
    path: "/aluno/:id",
    element: <TelaPerfilAluno />,
  },
  {
    path: "/gerenciar-professores",
    element: <TelaGerenciarProfessores />
  },
  {
    path: "/editar-perfil",
    element: <TelaEditarPerfil />
  },
  {
    path: "/adicionar-professor",
    element: <TelaAdicionarProfessor />
  },
  {
    path: "/vincular",
    element: <TelaVincularAlunoResponsavel />
  }
]);
