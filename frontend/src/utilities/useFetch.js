import { useState, useEffect } from "react";
import axios from "axios";

const useFetch = url => {
    const [status, setStatus] = useState("");
    const [fetchedData, setData] = useState([]);
    const [error, setError] = useState({});

    useEffect(() => {
        setStatus("loading");
        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [url]);

    const fetchData = () => {
        axios
            .get(url)
            .then(response => {
                setStatus("loaded");
                setData(response.data);
            })
            .catch(error => {
                setStatus("error");
                setError(error.response);
            });
    };

    return [status, error, fetchedData];
};

export default useFetch;
