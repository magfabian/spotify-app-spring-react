import React, { useState, createContext } from "react";

export const FavouriteContext = createContext();

export const FavouriteProvider = props => {
    const [artist, setArtist] = useState([]);
    const [track, setTrack] = useState([]);
    const [album, setAlbum] = useState([]);
    const [playlist, setPlaylist] = useState([]);
    return (
        <FavouriteContext.Provider
            value={{
                artists: [artist, setArtist],
                tracks: [track, setTrack],
                albums: [album, setAlbum],
                playlists: [playlist, setPlaylist]
            }}
        >
            {props.children}
        </FavouriteContext.Provider>
    );
};
