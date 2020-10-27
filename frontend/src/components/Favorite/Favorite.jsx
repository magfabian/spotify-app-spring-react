import React, { useState, useEffect } from "react";
import { Header, Divider } from "semantic-ui-react";
import CardItem from "../CardItem/CardItem";
import useFetch from "../../utilities/useFetch";
import url from "../../utilities/url";
import Error from "../Error/Error";
import axios from "axios";
import Loading from "../Loading/Loading";
import NotLoggedIn from "../NotLoggedIn/NotLoggedIn";

const Favorite = () => {
    const [status, setStatus] = useState("");
    const [fetchedData, setData] = useState([]);

    useEffect(() => {
        setStatus("loading");
        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const fetchData = () => {
        axios
            .get(url.favorite_all, {
                withCredentials: true,
            })
            .then((response) => {
                setStatus("loaded");
                setData(response.data);
            })
            .catch((error) => {
                setStatus("error");
            });
    };

    const headerStyle = {
        marginBottom: "20px",
    };

    const dividerStlye = {
        maxWidth: "940px",
    };

    const renderCards = (type, cards) => {
        return cards.map((data, index) => (
            <CardItem
                key={index}
                category={type}
                spotifyId={data.spotifyId}
                imageUrl={data.imageUrl}
                onClickUrl={data.onClickUrl}
                header={data.header}
                footer={data.footer}
                footerUrl={data.footerUrl}
                favorite={data.favorite}
                handleFavoriteDelete={handleFavoriteDelete}
            />
        ));
    };

    const handleFavoriteDelete = () => {
        setTimeout(() => {
            fetchData();
        }, 100);
    };

    let renderedArtists = [];
    if (fetchedData["artists"]) {
        renderedArtists = renderCards("artist", fetchedData["artists"]);
    }

    let renderedTracks = [];
    if (fetchedData["tracks"]) {
        renderedTracks = renderCards("track", fetchedData["tracks"]);
    }

    let renderedAlbums = [];
    if (fetchedData["albums"]) {
        renderedAlbums = renderCards("album", fetchedData["albums"]);
    }

    let renderedPlaylists = [];
    if (fetchedData["playlists"]) {
        renderedPlaylists = renderCards("playlist", fetchedData["playlists"]);
    }

    return (
        <div className="content">
            {status === "error" && (
                <div>
                    <NotLoggedIn />
                </div>
            )}
            {status === "loading" && <Loading />}
            {status === "loaded" && (
                <div>
                    <Header style={headerStyle} as="h1">
                        Favorites:
                    </Header>
                    {renderedArtists.length > 0 && (
                        <div>
                            <Divider style={dividerStlye} horizontal>
                                ARTISTS
                            </Divider>
                            <div className="ui stackable three column grid">
                                {renderedArtists}
                            </div>
                        </div>
                    )}
                    {renderedAlbums.length > 0 && (
                        <div>
                            <Divider style={dividerStlye} horizontal>
                                ALBUMS
                            </Divider>
                            <div className="ui stackable three column grid">
                                {renderedAlbums}
                            </div>
                        </div>
                    )}
                    {renderedTracks.length > 0 && (
                        <div>
                            <Divider style={dividerStlye} horizontal>
                                TRACKS
                            </Divider>
                            <div className="ui stackable three column grid">
                                {renderedTracks}
                            </div>
                        </div>
                    )}
                    {renderedPlaylists.length > 0 && (
                        <div>
                            <Divider style={dividerStlye} horizontal>
                                PLAYLISTS
                            </Divider>
                            <div className="ui stackable three column grid">
                                {renderedPlaylists}
                            </div>
                        </div>
                    )}
                </div>
            )}
        </div>
    );
};

export default Favorite;
