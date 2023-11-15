import { axiosInstance } from "../base/axiosInstance";

export async function cadastrarAdministradorApi(nome, siape, email){

    try{
        await axiosInstance.post("/administradores", {
            nome, siape, email
        })
    }
    catch(error){
        throw error?.response?.data?.message;
    }

}