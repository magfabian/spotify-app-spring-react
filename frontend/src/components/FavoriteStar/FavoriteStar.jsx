import React, { useContext } from "react";
import { Card, Icon } from "semantic-ui-react";
import { FavouriteContext } from "../../context/FavouriteContext";

const FavoriteStar = ({
    category,
    id,
    imageUrl,
    onClickUrl,
    header,
    footer,
    footerUrl,
}) => {
    const { artists, tracks, albums, playlists } = useContext(FavouriteContext);
    const [artist, setArtist] = artists;
    const [track, setTrack] = tracks;
    const [album, setAlbum] = albums;
    const [playlist, setPlaylist] = playlists;

    const starStyle = {
        marginLeft: "110px",
    };

    const handleClick = () => {
        const card = {
            id: id,
            imageUrl: imageUrl,
            onClickUrl: onClickUrl,
            header: header,
            footer: footer,
            footerUrl: footerUrl,
        };
        console.log(card);
        setCard(card);
    };

    const setCard = (card) => {
        switch (category) {
            case "album":
                if (
                    !album.some((object) => object.imageUrl === card.imageUrl)
                ) {
                    setAlbum((prevAlbums) => [...prevAlbums, card]);
                }
                break;
            case "artist":
                if (
                    !artist.some((object) => object.imageUrl === card.imageUrl)
                ) {
                    setArtist((prevArtists) => [...prevArtists, card]);
                }
                break;
            case "track":
                if (
                    !track.some((object) => object.imageUrl === card.imageUrl)
                ) {
                    setTrack((prevTracks) => [...prevTracks, card]);
                }
                break;
            case "playlist":
                if (
                    !playlist.some(
                        (object) => object.imageUrl === card.imageUrl
                    )
                ) {
                    setPlaylist((prevPlaylists) => [...prevPlaylists, card]);
                }
                break;
            default:
                break;
        }
    };

    return (
        <Card.Content extra>
            <span style={starStyle} onClick={handleClick}>
                <Icon name="star" size="large" />
            </span>
        </Card.Content>
    );
};

export default FavoriteStar;
