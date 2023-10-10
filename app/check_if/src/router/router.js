import { TelaInicial, TelaLogin, TelaRegistrarChegadaAluno } from "../ui/screen/";

const { createBrowserRouter } = require("react-router-dom");
  
export const router = createBrowserRouter([
    {
      path: "/",
      element: <TelaInicial />,
    },
    {
      path: "/login",
      element: <TelaLogin/>
    },
    {
      path: "/registrar-chegada",
      element: <TelaRegistrarChegadaAluno />
    }
]);
  