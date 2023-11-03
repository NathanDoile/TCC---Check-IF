import { axiosInstance } from "../base/axiosInstance";

export async function esqueceuSenhaApi(email) {

    try {
        await axiosInstance.post("/esqueceu-senha", {
            email,
        });

    } catch (error) {
        throw new Error(error?.response?.data?.message);
    }
}