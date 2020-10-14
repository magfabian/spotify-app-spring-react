import React, { useEffect, useState } from "react";
import url from "../../utilities/url";
import CardItem from "../CardItem/CardItem";
import Loading from "../Loading/Loading";
import { Header, Divider } from "semantic-ui-react";
import Error from "../Error/Error";
import axios from "axios";
import EmptyPlaylist from "../EmptyPlaylist/EmptyPlaylist";

const Playlist = (props) => {
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
            .get(url.playlist + props.match.params.title)
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

    const reloadPlaylist = () => {
        setTimeout(() => {
            fetchData();
        }, 100);
    };

    const renderCards = () => {
        if (fetchedData.total > 0) {
            return fetchedData.userPlaylistTracks.map((card) => (
                <CardItem
                    key={card.spotifyId}
                    category="track"
                    spotifyId={card.spotifyId}
                    imageUrl={card.imageUrl}
                    onClickUrl={card.onClickUrl}
                    header={card.header}
                    footer={card.footer}
                    footerUrl={card.footerUrl}
                    favorite={card.favorite}
                    playlistId={fetchedData.id}
                    reloadPlaylist={reloadPlaylist}
                />
            ));
        } else {
            return;
        }
    };

    const headerStyle = {
        marginBottom: "20px",
    };

    const dividerStlye = {
        maxWidth: "940px",
    };

    let renderedCards = renderCards();

    return (
        <div className="content ">
            {status === "error" && <Error error={error} />}
            {status === "loading" && <Loading />}
            {status === "loaded" && (
                <div>
                    <Header style={headerStyle} as="h1">
                        {props.match.params.title}
                    </Header>
                    {fetchedData.total === 0 && <EmptyPlaylist />}
                    {fetchedData.total > 0 && (
                        <>
                            <Divider style={dividerStlye} horizontal>
                                TRACKS
                            </Divider>
                            <div className="ui stackable three column grid">
                                {renderedCards}
                            </div>
                        </>
                    )}
                </div>
            )}
        </div>
    );
};

export default Playlist;
