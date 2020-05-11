import axios  from "axios";

export const discoverAll = () =>
    axios.get('https://localhost:8090/api/Discovery/en-GB/HKD/');