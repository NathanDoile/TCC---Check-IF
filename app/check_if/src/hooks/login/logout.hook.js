import { useState } from "react";
import { logoutApi } from "../../api";
//import useGlobalAdmin from "../../context/admin/admin.context";
//import useGlobalUsuario from "../../context/usuario/usuario.context";

export function useLogoutHook() {
  const [error, setError] = useState(false);
  //const [, setUser] = useGlobalUsuario();
  //const [, setAdmin] = useGlobalAdmin();

  async function logout() {
    try {
      await logoutApi();
      //    setUser(null);
      //    setAdmin(null);
    } catch (error) {
      setError(error.response.data.message);
    }
  }

  return {
    error,
    logout,
  };
}
