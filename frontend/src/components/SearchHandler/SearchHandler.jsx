import React from "react";
import useFetch from "../../utilities/useFetch";
import Loading from "../Loading/Loading";
import Error from "../Error/Error";
import { Divider } from "semantic-ui-react";
import CardItem from "../CardItem/CardItem";

const SearchHandler = ({ urlString, categoryType }) => {
    const [status, error, fetchedData] = useFetch(urlString);

    const dividerStyle = {
        maxWidth: "940px",
    };

    const renderedCard = (data) => {
        return (
            <CardItem
                key={data.spotifyId}
                category={categoryType}
                spotifyId={data.spotifyId}
                imageUrl={data.imageUrl}
                onClickUrl={data.onClickUrl}
                header={data.header}
                footer={data.footer}
                footerUrl={data.footerUrl}
            />
        );
    };

    return (
        <div>
            {status === "error" && <Error error={error} />}
            {status === "loading" && <Loading />}
            {status === "loaded" && (
                <div>
                    <Divider style={dividerStyle} horizontal>
                        {categoryType}
                    </Divider>
                    <div className="ui stackable three column grid">
                        {fetchedData.map((data) => renderedCard(data))}
                    </div>
                </div>
            )}
        </div>
    );
};

export default SearchHandler;
