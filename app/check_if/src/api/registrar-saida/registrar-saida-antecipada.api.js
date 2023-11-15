import { axiosInstance } from "../base/axiosInstance";

export async function registrarSaidaAntecipadaApi(nomeResponsavel, grauParentesco, motivo, matriculaAluno) {

    try {
        await axiosInstance.post("/saidas-antecipadas/cadastrar", {
            nomeResponsavel, grauParentesco, motivo, matriculaAluno
        })
    }
    catch(error){
        throw error?.response?.data?.message;
    }

}