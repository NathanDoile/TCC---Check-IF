import { useState } from "react";
import { logoutApi } from "../../api";
import { useNavigate } from "react-router-dom";

export function useLogoutHook() {
  const [error, setError] = useState(false);

  const navigate = useNavigate();

  async function logout() {
    try {
      await logoutApi();

      localStorage.clear();

      navigate("/");
    } catch (error) {
      setError(error.response.data.message);
    }
  }

  return {
    error,
    logout,
  };
}
