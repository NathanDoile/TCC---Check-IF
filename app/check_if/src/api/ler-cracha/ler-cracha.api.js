import { axiosInstance } from "../base/axiosInstance";

export async function lerCrachaApi(fotoCracha) {
  try {
    const response = await axiosInstance.post("/alunos/ler-cracha/publico", {
      fotoCracha,
    });

    return response.data;
  } catch (error) {
    throw new Error(error?.response?.data?.message);
  }
}
