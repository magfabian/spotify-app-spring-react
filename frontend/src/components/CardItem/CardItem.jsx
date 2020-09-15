import React from "react";
import { Card, Image } from "semantic-ui-react";
import FavoriteStar from "../FavoriteStar/FavoriteStar";

const cardStyle = {
    margin: "10px",
    maxWidth: "300px",
};

const CardItem = ({
    category,
    img,
    imageUrl,
    header,
    headerUrl,
    footer,
    id,
}) => {
    return (
        <Card key={id} style={cardStyle} className="column">
            <Image
                src={img}
                alt=""
                wrapped
                as="a"
                ui={false}
                href={imageUrl}
                target="_blank"
            />
            <Card.Content>
                <Card.Header>{footer}</Card.Header>
                <Card.Description as="a" href={headerUrl} target="_blank">
                    {header}
                </Card.Description>
            </Card.Content>
            <FavoriteStar
                category={category}
                img={img}
                imgUrl={imageUrl}
                header={header}
                headerUrl={headerUrl}
                footer={footer}
            />
        </Card>
    );
};

export default CardItem;
