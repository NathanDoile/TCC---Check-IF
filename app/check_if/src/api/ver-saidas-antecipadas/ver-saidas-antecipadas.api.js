import { axiosInstance} from '../base/axiosInstance';

export async function verSaidasAntecipadasApi(page, data){

    try{
        const response = await axiosInstance.get(`/responsaveis/saidas-antecipadas/${data}?size=8&page=${page}`);

        return response.data;
    }
    catch(error){
        throw error?.response?.data?.message;
    }
}