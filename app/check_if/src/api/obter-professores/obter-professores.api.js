import { axiosInstance } from "../base/axiosInstance";

export async function obterProfessoresApi() {

    try {
        const response = await axiosInstance.get("/professores/publico");

        return response.data;
    } catch (error) {
        throw new Error(error?.response?.data?.message);
    }
}