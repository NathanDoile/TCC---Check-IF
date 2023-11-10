import "./App.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { router } from "./router/index";
import { RouterProvider } from "react-router-dom";
import { GlobalUsuarioProvider } from "./context/usuario/usuario.context";
import { GlobalUsuarioEsqueceuSenhaProvider } from "./context/esqueceu-senha/esqueceu-senha.context";

function App() {
  return (
    <>
      <GlobalUsuarioEsqueceuSenhaProvider>
        <GlobalUsuarioProvider>
          <ToastContainer />
          <RouterProvider router={router} />
        </GlobalUsuarioProvider>
      </GlobalUsuarioEsqueceuSenhaProvider>
    </>
  );
}

export default App;
