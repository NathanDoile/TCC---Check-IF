import axios from "axios";
import { API_URL } from "../../constants/api.constants";

export const axiosInstance = axios.create({
  baseURL: API_URL,
  timeout: 20000,
  withCredentials: true,
});
