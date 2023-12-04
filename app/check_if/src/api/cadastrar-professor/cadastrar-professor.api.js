import { axiosInstance } from "../base/axiosInstance";

export async function cadastrarProfessorApi(nome, email, siape, celular){

    try{
        await axiosInstance.post("/professores", {
            nome, siape, email, celular
        })
    }
    catch(error){
        throw error?.response?.data?.message;
    }
}