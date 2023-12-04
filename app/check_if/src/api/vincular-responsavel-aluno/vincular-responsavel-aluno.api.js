import { axiosInstance } from "../base/axiosInstance";

export async function vincularResponsavelAlunoApi(matricula, email) {

    try {
        await axiosInstance.put("/responsaveis/vincular", {
            matricula, email
        })
    }
    catch (error) {
        throw error?.response?.data?.message;
    }
}