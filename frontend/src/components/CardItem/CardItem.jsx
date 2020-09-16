import React from "react";
import { Card, Image } from "semantic-ui-react";
import FavoriteStar from "../FavoriteStar/FavoriteStar";

const cardStyle = {
    margin: "10px",
    maxWidth: "300px"
};

const CardItem = ({
    category,
    id,
    imageUrl,
    onClickUrl,
    header,
    footer,
    footerUrl
}) => {
    return (
        <Card style={cardStyle} className='column'>
            <Image
                src={imageUrl}
                alt=''
                wrapped
                as='a'
                ui={false}
                href={onClickUrl}
                target='_blank'
            />
            <Card.Content>
                <Card.Header>{header}</Card.Header>
                <Card.Description as='a' href={footerUrl} target='_blank'>
                    {footer}
                </Card.Description>
            </Card.Content>
            <FavoriteStar
                category={category}
                id={id}
                imageUrl={imageUrl}
                onClickUrl={onClickUrl}
                header={header}
                footer={footer}
                footerUrl={footerUrl}
            />
        </Card>
    );
};

export default CardItem;
