import {
  TelaInicial,
  TelaLogin,
  TelaRegistrarChegadaAluno,
  TelaEsqueceuSenha,
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
  TelaVincularAlunoResponsavel,
  TelaAlterarSenhaPadrao,
  TelaAlterarSenhaInicial,
  TelaVerSolicitacoes,
  TelaGerenciarNotificacoes,
  TelaPaginaNaoEncontrada,
} from "../ui/screen/";
import { PrivateRouteAdministrador, PrivateRouteResponsavel, PrivateRouteHome, PrivateRouteLogado } from "./private";

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
    element: <PrivateRouteHome />,
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
    element: <PrivateRouteAdministrador Screen={TelaRegistrarChegadaAdministrador} />,
  },
  {
    path: "/registrar-saida-antecipada",
    element: <PrivateRouteAdministrador Screen={TelaRegistrarSaidaAntecipada} />,
  },
  {
    path: "/cadastrar-administrador",
    element: <PrivateRouteAdministrador Screen={TelaCadastrarAdministrador} />,
  },
  {
    path: "/cadastrar-responsavel",
    element: <PrivateRouteAdministrador Screen={TelaCadastrarResponsavel} />,
  },
  {
    path: "/cadastrar-responsavel/lote",
    element: <PrivateRouteAdministrador Screen={TelaCadastrarResponsavelLote} />,
  },
  {
    path: "/pesquisar/:texto",
    element: <PrivateRouteAdministrador Screen={TelaPesquisarAluno} />,
  },
  {
    path: "/aluno/:id",
    element: <PrivateRouteAdministrador Screen={TelaPerfilAluno} />,
  },
  {
    path: "/gerenciar-professores",
    element: <PrivateRouteAdministrador Screen={TelaGerenciarProfessores} />,
  },
  {
    path: "/editar-perfil",
    element: <PrivateRouteLogado Screen={TelaEditarPerfil} />,
  },
  {
    path: "/adicionar-professor",
    element: <PrivateRouteAdministrador Screen={TelaAdicionarProfessor} />,
  },
  {
    path: "/vincular",
    element: <PrivateRouteAdministrador Screen={TelaVincularAlunoResponsavel} />,
  },
  {
    path: "/alterar-senha",
    element: <PrivateRouteLogado Screen={TelaAlterarSenhaPadrao} />,
  },
  {
    path: "/alterar-senha-inicial",
    element: <PrivateRouteLogado Screen={TelaAlterarSenhaInicial} />,
  },
  {
    path: "/solicitacoes",
    element: <PrivateRouteResponsavel Screen={TelaVerSolicitacoes} />,
  },
  {
    path: "/notificacoes",
    element: <PrivateRouteResponsavel Screen={TelaGerenciarNotificacoes} />,
  },
  {
    path: "*",
    element: <TelaPaginaNaoEncontrada />,
  },
]);
