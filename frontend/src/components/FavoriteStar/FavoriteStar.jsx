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
}) => {
    const starStyle = {
        marginLeft: "110px",
    };

    const handleClick = () => {
        const card = {
            spotifyId: spotifyId,
            imageUrl: imageUrl,
            onClickUrl: onClickUrl,
            header: header,
            footer: footer,
            footerUrl: footerUrl,
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

    return (
        <Card.Content extra>
            <span style={starStyle}>
                <Icon name="star" size="large" onClick={handleClick} />
            </span>
        </Card.Content>
    );
};

export default FavoriteStar;
