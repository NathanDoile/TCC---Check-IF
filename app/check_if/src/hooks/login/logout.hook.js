import { useState } from "react";
import { logoutApi } from "../../api";

export function useLogoutHook() {
  const [error, setError] = useState(false);

  async function logout() {
    try {
      await logoutApi();
      
      localStorage.clear();
    } catch (error) {
      setError(error.response.data.message);
    }
  }

  return {
    error,
    logout,
  };
}
