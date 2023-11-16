import { axiosInstance } from "../base/axiosInstance";

export async function alterarSenhaResponsavel(senhaAntiga, senhaNova){

    try{
        await axiosInstance.put("/responsaveis/senha", {
            senhaAntiga, senhaNova
        })
    }
    catch(error){
        throw error?.response?.data?.message;
    }
}