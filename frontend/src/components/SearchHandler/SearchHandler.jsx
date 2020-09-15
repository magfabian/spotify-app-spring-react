import React from "react";
import useFetch from "../../utilities/useFetch";
import Loading from "../Loading/Loading";
import Error from "../Error/Error";
import ArtistCard from "../Cards/ArtistCard/ArtistCard";
import AlbumCard from "../Cards/AlbumCard/AlbumCard";
import PlaylistCard from "../Cards/PlaylistCard/PlaylistCard";
import TrackCard from "../Cards/TrackCard/TrackCard";
import { Divider } from "semantic-ui-react";

const SearchHandler = ({ urlString, categoryType }) => {
    const [status, error, fetchedData] = useFetch(urlString, categoryType);
    // console.log(fetchedData);

    const dividerStyle = {
        maxWidth: "940px",
    };

    const renderedCard = (data) => {
        console.log(data.name + data.id);
        switch (categoryType) {
            case "artist":
                return <ArtistCard data={data} />;
            case "album":
                return <AlbumCard data={data} />;
            case "playlist":
                return <PlaylistCard data={data} />;
            case "track":
                return <TrackCard data={data} />;
            default:
                return <></>;
        }
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
