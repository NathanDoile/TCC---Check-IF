import {
  TelaInicial,
  TelaLogin,
  TelaRegistrarChegadaAluno,
  TelaEsqueceuSenha,
  TelaHome,
  TelaEsqueceuSenhaToken,
  TelaEsqueceuSenhaAlterar,
  TelaRegistrarChegadaAdministrador
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
    element: <TelaEsqueceuSenhaToken />
  },
  {
    path: "/esqueceu-senha/alterar",
    element: <TelaEsqueceuSenhaAlterar />
  },
  {
    path: "/administrador/registrar-chegada",
    element: <TelaRegistrarChegadaAdministrador />
  }
]);
