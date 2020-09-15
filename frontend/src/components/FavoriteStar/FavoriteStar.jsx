import React, { useContext } from "react";
import { Card, Icon } from "semantic-ui-react";
import { FavouriteContext } from "../../context/FavouriteContext";

const FavoriteStar = ({
    category,
    img,
    imageUrl,
    header,
    headerUrl,
    footer
}) => {
    const { artists, tracks, albums, playlists } = useContext(FavouriteContext);
    const [artist, setArtist] = artists;
    const [track, setTrack] = tracks;
    const [album, setAlbum] = albums;
    const [playlist, setPlaylist] = playlists;

    const starStyle = {
        marginLeft: "110px"
    };

    const handleClick = () => {
        const card = {
            img: img,
            imageUrl: imageUrl,
            header: header,
            headerUrl,
            footer
        };
        setCard(card);
    };

    const setCard = card => {
        switch (category) {
            case "album":
                if (!album.some(object => object.img === card.img)) {
                    setAlbum(prevAlbums => [...prevAlbums, card]);
                }
                break;
            case "artist":
                if (!artist.some(object => object.img === card.img)) {
                    setArtist(prevArtists => [...prevArtists, card]);
                }
                break;
            case "track":
                if (!track.some(object => object.img === card.img)) {
                    setTrack(prevTracks => [...prevTracks, card]);
                }
                break;
            case "playlist":
                if (!playlist.some(object => object.img === card.img)) {
                    setPlaylist(prevPlaylists => [...prevPlaylists, card]);
                }
                break;
            default:
                break;
        }
    };

    return (
        <Card.Content extra>
            <span style={starStyle} onClick={handleClick}>
                <Icon name='star' size='large' />
            </span>
        </Card.Content>
    );
};

export default FavoriteStar;
