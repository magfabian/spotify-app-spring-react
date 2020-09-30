import React from "react";
import useFetch from "../../utilities/useFetch";
import url from "../../utilities/url";
import CardItem from "../CardItem/CardItem";
import Loading from "../Loading/Loading";
import { Header, Divider } from "semantic-ui-react";
import Error from "../Error/Error";
import EmptyPlaylist from "../EmptyPlaylist/EmptyPlaylist";

const Playlist = (props) => {
    const [status, error, fetchedData] = useFetch(
        url.playlist + props.match.params.title
    );

    const renderCards = () => {
        if (fetchedData.total > 0) {
            return fetchedData.userPlaylistTracks.map((card) => (
                <CardItem
                    key={card.spotifyId}
                    category="track"
                    spotifyId={card.spotifyId}
                    imageUrl={card.imageUrl}
                    onClickUrl={card.onClickUrl}
                    header={card.header}
                    footer={card.footer}
                    footerUrl={card.footerUrl}
                />
            ));
        } else {
            return;
        }
    };

    const headerStyle = {
        marginBottom: "20px",
    };

    const dividerStlye = {
        maxWidth: "940px",
    };

    let renderedCards = renderCards();

    return (
        <div className="content ">
            {status === "error" && <Error error={error} />}
            {status === "loading" && <Loading />}
            {status === "loaded" && (
                <div>
                    <Header style={headerStyle} as="h1">
                        {props.match.params.title}
                    </Header>
                    {fetchedData.total === 0 && <EmptyPlaylist />}
                    {fetchedData.total > 0 && (
                        <>
                            <Divider style={dividerStlye} horizontal>
                                TRACKS
                            </Divider>
                            <div className="ui stackable three column grid">
                                {renderedCards}
                            </div>
                        </>
                    )}
                </div>
            )}
        </div>
    );
};

export default Playlist;
