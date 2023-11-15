import { axiosInstance } from "../base/axiosInstance";

export async function buscarTodosProfessorApi() {

    try {
        const response = await axiosInstance.get('/professores/publico')

        return response.data
    }
    catch (error) {
        throw error?.response?.data?.message;
    }

}