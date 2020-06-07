import axios  from "axios";

export const discoverAll = (lang, curr) =>
    axios.get(`https://localhost:8090/api/Discovery/${lang}/${curr}/`);