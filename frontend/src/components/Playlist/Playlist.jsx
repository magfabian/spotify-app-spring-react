import React from "react";
import useFetch from "../../utilities/useFetch";
import url from "../../utilities/url";
import CardItem from "../CardItem/CardItem";
import Loading from "../Loading/Loading";
import { Header, Divider } from "semantic-ui-react";
import Error from "../Error/Error";

const Playlist = ({ name }) => {
    const [status, error, fetchedData] = useFetch(url.playlist + { name });

    const renderedCards = fetchedData.map((card) => (
        <CardItem
            key={card.id}
            id={card.id}
            category="track"
            imageUrl={card.imageUrl}
            onClickUrl={card.onClickUrl}
            header={card.header}
            footer={card.footer}
            footerUrl={card.footerUrl}
        />
    ));

    const headerStyle = {
        marginBottom: "20px",
    };

    const dividerStlye = {
        maxWidth: "940px",
    };

    return (
        <div className="content ">
            {status === "error" && <Error error={error} />}
            {status === "loading" && <Loading />}
            {status === "loaded" && (
                <div>
                    <Header style={headerStyle} as="h1">
                        {name}:
                    </Header>
                    <Divider style={dividerStlye} horizontal>
                        TRACKS
                    </Divider>
                    <div className="ui stackable three column grid">
                        {renderedCards}
                    </div>
                </div>
            )}
        </div>
    );
};

export default Playlist;
