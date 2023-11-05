import { axiosInstance } from "../base/axiosInstance";

export async function esqueceuSenhaAlterarApi(senha, email, token){

    try {
        await axiosInstance.post("/esqueceu-senha/atualizar", {
            senha, email, token
        });

    } catch (error) {
        throw error?.response?.data;
    }
}