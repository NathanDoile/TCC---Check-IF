import "./App.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { router } from "./router/index";
import { RouterProvider } from "react-router-dom";
import { GlobalUsuarioProvider } from "./context/usuario/usuario.context";

function App() {
  return (
    <>
      <GlobalUsuarioProvider>
        <ToastContainer />
        <RouterProvider router={router} />
      </GlobalUsuarioProvider>
    </>
  );
}

export default App;
