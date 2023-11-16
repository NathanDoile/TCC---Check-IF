import { axiosInstance } from "../base/axiosInstance";

export async function obterSaidasAntecipadas(){

    try{
        const response = await axiosInstance.get("/saidas-antecipadas");

        return response.data;
    }
    catch(error){
        throw error?.response?.data?.message;
    }
}