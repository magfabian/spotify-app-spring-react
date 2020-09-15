import { useState, useEffect } from "react";
import axios from "axios";
import token from "./token";

const useFetch = (url, route) => {
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
                setData(getObjectRoute(response));
            })
            .catch(error => {
                setStatus("error");
                setError(error.response);
            });
    };

    const getObjectRoute = response => {
        switch (route) {
            case "release":
                return response.data;
            case "artist":
                return response.data.artists.items;
            case "track":
                return response.data.tracks.items;
            case "playlist":
                return response.data.playlists.items;
            case "album":
                return response.data.albums.items;
            default:
                return "error";
        }
    };

    return [status, error, fetchedData];
};

export default useFetch;
