import { axiosInstance } from "../base/axiosInstance";

export async function obterAlunosPorResponsavelApi(){

    try{

        const response = await axiosInstance.get("/alunos/obter/responsavel");

        return response.data;
    }
    catch(error){
        throw error?.response?.data?.message;
    }
}