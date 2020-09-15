import React from "react";
import url from "../../../utilities/url";
import { Card, Image } from "semantic-ui-react";
import FavoriteStar from "../../FavoriteStar/FavoriteStar";

const cardStyle = {
    margin: "10px",
    maxWidth: "300px",
};

const ArtistCard = ({ data }) => {
    const imageCondition =
        data.images[0] === undefined ? url.noImg : data.images[0].url;
    return (
        <Card key={data.id} style={cardStyle} className="column">
            <Image
                src={imageCondition}
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
                    Followers: {data.followers.total}
                </Card.Description>
            </Card.Content>
            <FavoriteStar
                category="artist"
                img={imageCondition}
                imgUrl={data.external_urls.spotify}
                header={"Followers:" + data.followers.total}
                headerUrl={data.external_urls.spotify}
                footer={data.name}
            />
        </Card>
    );
};

export default ArtistCard;
