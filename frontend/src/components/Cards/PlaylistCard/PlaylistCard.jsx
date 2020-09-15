import React from "react";
import { Card, Image } from "semantic-ui-react";
import FavoriteStar from "../../FavoriteStar/FavoriteStar";

const cardStyle = {
    margin: "10px",
    maxWidth: "300px",
};

const PlaylistCard = ({ data }) => {
    return (
        <Card key={data.id} style={cardStyle} className="column">
            <Image
                src={data.images[0].url}
                alt=""
                wrapped
                as="a"
                ui={false}
                href={data.external_urls.spotify}
                target="_blank"
            />
            <Card.Content>
                <Card.Header>{data.name}</Card.Header>
                <Card.Description
                    as="a"
                    href={data.external_urls.spotify}
                    target="_blank"
                >
                    Tracks: {data.tracks.total}
                </Card.Description>
            </Card.Content>
            <FavoriteStar
                category="playlist"
                img={data.images[0].url}
                imgUrl={data.external_urls.spotify}
                header={"Tracks:" + data.tracks.total}
                headerUrl={data.external_urls.spotify}
                footer={data.name}
            />
        </Card>
    );
};

export default PlaylistCard;
