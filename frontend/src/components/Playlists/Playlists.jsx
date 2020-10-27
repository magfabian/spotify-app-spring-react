import React, { useState, useEffect } from "react";
import PlaylistModal from "../PlaylistModal/PlaylistModal";
import url from "../../utilities/url";
import { Header, Segment } from "semantic-ui-react";
import Loading from "../Loading/Loading";
import Error from "../Error/Error";
import { Link } from "react-router-dom";
import axios from "axios";
import NotLoggedIn from "../NotLoggedIn/NotLoggedIn";

const Playlists = () => {
    const [status, setStatus] = useState("");
    const [fetchedData, setData] = useState([]);
    const [error, setError] = useState({});

    useEffect(() => {
        setStatus("loading");
        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const fetchData = async () => {
        await axios
            .get(url.playlist_get_all)
            .then((response) => {
                setStatus("loaded");
                setData(response.data);
                console.log(response.data);
            })
            .catch((error) => {
                setError(error.response);
                setStatus("error");
            });
    };

    const square = {
        width: 190,
        height: 190,
        margin: "20px",
        marginTop: "50px",
    };

    const headerStyle = {
        display: "table",
        position: "relative",
        top: "1.9vw",
        margin: "0 auto",
        fontWeight: "bold",
        fontSize: "30px",
    };

    const subheaderStyle = {
        fontSize: "20px",
    };

    const linkStyleWhite = {
        color: "black",
    };

    const linkStyleBlack = {
        color: "white",
    };

    const handleClick = () => {
        setTimeout(() => {
            fetchData();
        }, 100);
    };

    const renderedPlaylists = fetchedData.map((data, index) => {
        const title = data.title.replace(/%20/g, " ");
        const playListUrl = `/playlists/${title}`;
        if (index === 0 || index % 2 === 0) {
            return (
                <Segment key={index} circular style={square}>
                    <Header style={headerStyle} as="h2">
                        <Link to={playListUrl} style={linkStyleWhite}>
                            {title}
                        </Link>
                        <Header.Subheader
                            style={subheaderStyle}
                            onClick={handleClick}
                        >
                            Songs: {data.total}
                        </Header.Subheader>
                    </Header>
                </Segment>
            );
        } else {
            return (
                <Segment key={index} circular inverted style={square}>
                    <Header style={headerStyle} as="h2" inverted>
                        <Link to={playListUrl} style={linkStyleBlack}>
                            {title}
                        </Link>
                        <Header.Subheader style={subheaderStyle}>
                            Songs: {data.total}
                        </Header.Subheader>
                    </Header>
                </Segment>
            );
        }
    });

    return (
        <div>
            {status === "error" && (
                <div>
                    <NotLoggedIn />
                </div>
            )}
            {status === "loading" && <Loading />}
            {status === "loaded" && (
                <div>
                    <PlaylistModal handleClick={handleClick.bind(this)} />
                    <div className="ui grid">{renderedPlaylists}</div>
                </div>
            )}
        </div>
    );
};

export default Playlists;
