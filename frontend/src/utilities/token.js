import axios from "axios";
import url from "./url";

const token = {
    getToken: async function () {
        const response = await axios.get(url.token);
        const token = response.data.token;
        return token;
    },
};

export default token;
