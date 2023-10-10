import { axiosInstance } from "../base/axiosInstance";

export async function logarApi(email, senha) {
  try {
    const resonse = await axiosInstance.post(
      "/login",
      {},
      {
        auth: {
          username: email,
          password: senha,
        },
      }
    );
    return resonse.data;
  } catch (error) {
    if (error.response.status === 401) {
      throw new Error("Acesso não autorizado");
    }
    throw new Error(error.response.data.message);
  }
}
