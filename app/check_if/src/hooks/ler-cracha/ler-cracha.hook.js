import { lerCrachaApi } from "../../api";
import { toast } from "react-toastify";

export function useLerCracha(){

    async function lerCracha(fotoCracha) {
        try {
          const response = await lerCrachaApi(fotoCracha);
          
          return response;
        } 
        catch (error) {
            console.log("OLÁ")
            toast.error(`${error}`);
        }
      }
    
      return { lerCracha };
}