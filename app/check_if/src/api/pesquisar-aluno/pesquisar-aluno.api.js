import { axiosInstance } from "../base/axiosInstance";

export async function pesquisarAlunoApi(texto, page) {
  
  try {
    
    const response = await axiosInstance.get(
      `/alunos/pesquisar?texto=${texto}&size=8&page=${page}`
    );
    
    return response.data;
  } catch (error) {
    throw error?.response?.data;
  }
}
