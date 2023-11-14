import { axiosInstance } from "../base/axiosInstance";

export async function registrarChegadaManualApi(motivo, disciplina, matriculaAluno, idProfessor){

    try{
        await axiosInstance.post("/chegadas-atrasadas/registrar/manual", {
            motivo, disciplina, matriculaAluno, idProfessor
        });
    }
    catch(error){
        throw error?.response?.data?.message;
    }
}