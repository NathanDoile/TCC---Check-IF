import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { logarApi } from "../../api";
import useGlobalUsuario from "../../context/usuario/usuario.context";
import { toast } from "react-toastify";

export function useLogin() {
  const [, setUsuario] = useGlobalUsuario();
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  async function fazerLogin(email, senha) {
    try {
      const response = await logarApi(email, senha);

      setUsuario(response);

      navigate("/home");
    } catch (error) {

      setError(true);

      toast.error(`${error}`);
    }
  }

  return { error, fazerLogin };
}
