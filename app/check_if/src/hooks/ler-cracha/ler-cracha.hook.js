import { lerCrachaApi } from "../../api";
import { toast } from "react-toastify";

export function useLerCracha() {
  async function lerCracha(fotoCracha) {
    try {
      const response = await lerCrachaApi(fotoCracha);

      return response;
    } catch (error) {
      toast.error(`${error}`);
      throw new Error();
    }
  }

  return { lerCracha };
}
