import React from "react";
import { Card, Icon } from "semantic-ui-react";
import axios from "axios";
import url from "../../utilities/url";

const FavoriteStar = ({
    category,
    spotifyId,
    imageUrl,
    onClickUrl,
    header,
    footer,
    footerUrl,
    favorite,
}) => {
    const starStyle = {
        marginLeft: "110px",
    };

    const handleClick = (event) => {
        event.target.className = "yellow star large icon";
        const card = {
            spotifyId: spotifyId,
            imageUrl: imageUrl,
            onClickUrl: onClickUrl,
            header: header,
            footer: footer,
            footerUrl: footerUrl,
            favorite: favorite,
        };
        handlePost(card);
    };

    const handlePost = (card) => {
        switch (category) {
            case "track":
                axios.post(url.favorite_track, card);
                break;
            case "album":
                axios.post(url.favorite_album, card);
                break;
            case "playlist":
                axios.post(url.favorite_playlist, card);
                break;
            case "artist":
                axios.post(url.favorite_artist, card);
                break;
            default:
                break;
        }
    };

    const handleYellowClick = (event) => {
        event.target.className = "star large icon";
        const card = {
            spotifyId: spotifyId,
            imageUrl: imageUrl,
            onClickUrl: onClickUrl,
            header: header,
            footer: footer,
            footerUrl: footerUrl,
            favorite: favorite,
        };
        handleDelete(card);
    };

    const handleDelete = (card) => {
        switch (category) {
            case "track":
                axios.post(url.favorite_delete_track, card);
                break;
            case "album":
                axios.post(url.favorite_delete_album, card);
                break;
            case "playlist":
                axios.post(url.favorite_delete_playlist, card);
                break;
            case "artist":
                axios.post(url.favorite_delete_artist, card);
                break;
            default:
                break;
        }
    };

    return (
        <Card.Content extra>
            <span style={starStyle}>
                {favorite === true ? (
                    <Icon
                        name="yellow star"
                        size="large"
                        onClick={handleYellowClick}
                    />
                ) : (
                    <Icon name="star" size="large" onClick={handleClick} />
                )}
            </span>
        </Card.Content>
    );
};

export default FavoriteStar;
