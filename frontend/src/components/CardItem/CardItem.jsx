import React from "react";
import { Card, Image, Dropdown } from "semantic-ui-react";
import FavoriteStar from "../FavoriteStar/FavoriteStar";
import useFetch from "../../utilities/useFetch";
import url from "../../utilities/url";
import axios from "axios";

const cardStyle = {
    margin: "10px",
    maxWidth: "300px",
};

const CardItem = ({
    category,
    spotifyId,
    imageUrl,
    onClickUrl,
    header,
    footer,
    footerUrl,
    favorite,
    playlistId,
    reloadPlaylist,
}) => {
    const [status, error, fetchedData] = useFetch(url.playlist_get_all);

    const handleClick = (event) => {
        const playlist = event.target.getAttribute("data-title");
        const card = {
            spotifyId: spotifyId,
            imageUrl: imageUrl,
            onClickUrl: onClickUrl,
            header: header,
            footer: footer,
            footerUrl: footerUrl,
            favorite: favorite,
        };
        const trackUrl = url.playlist_add_track + playlist;
        axios.post(trackUrl, card);
    };

    const handleDelete = () => {
        const card = {
            spotifyId: spotifyId,
            imageUrl: imageUrl,
            onClickUrl: onClickUrl,
            header: header,
            footer: footer,
            footerUrl: footerUrl,
            favorite: favorite,
        };
        axios.post(url.playlist_delete_track + playlistId, card);
        reloadPlaylist();
    };

    const renderedDropDown = fetchedData.map((data, index) => {
        return (
            <Dropdown.Item
                key={index}
                onClick={handleClick}
                data-title={data.title}
            >
                {data.title}
            </Dropdown.Item>
        );
    });

    return (
        <Card style={cardStyle} className="column">
            <Image
                src={imageUrl}
                alt=""
                wrapped
                as="a"
                ui={false}
                href={onClickUrl}
                target="_blank"
            />
            <Card.Content>
                <Card.Header>{header}</Card.Header>
                <Card.Description as="a" href={footerUrl} target="_blank">
                    {footer}
                </Card.Description>
            </Card.Content>
            <FavoriteStar
                category={category}
                spotifyId={spotifyId}
                imageUrl={imageUrl}
                onClickUrl={onClickUrl}
                header={header}
                footer={footer}
                footerUrl={footerUrl}
                favorite={favorite}
            />
            {category === "track" && playlistId === undefined && (
                <Dropdown
                    text="Add to playlist"
                    icon="plus"
                    labeled
                    button
                    className="icon"
                >
                    <Dropdown.Menu>{renderedDropDown}</Dropdown.Menu>
                </Dropdown>
            )}
            {category === "track" && playlistId !== undefined && (
                <Dropdown
                    text="Remove from playlist"
                    icon="minus"
                    labeled
                    button
                    className="icon"
                    onClick={handleDelete}
                ></Dropdown>
            )}
        </Card>
    );
};

export default CardItem;
