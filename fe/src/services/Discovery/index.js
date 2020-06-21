import { instance as axios } from "../../components/Layout/Helpers/api/axios";

export const discoverAll = () =>
    axios.get(`https://localhost:8090/api/Discovery`);