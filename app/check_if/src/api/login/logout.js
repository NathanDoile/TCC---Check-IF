import { axiosInstance } from "../base/axiosInstance";

export async function logoutApi() {
  try {
    await axiosInstance.post("/logout", {});
  } catch (error) {
    throw new Error(error.response.data.message);
  }
}
