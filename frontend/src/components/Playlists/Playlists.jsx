import React from "react";
import PlaylistModal from "../PlaylistModal/PlaylistModal";
import url from "../../utilities/url";
import useFetch from "../../utilities/useFetch";
import { Header, Segment } from "semantic-ui-react";

const Playlists = () => {
    const [status, error, fetchedData] = useFetch(url.playlist_get_total);

    const square = {
        width: 190,
        height: 190,
        margin: "20px",
        marginTop: "50px",
    };

    const headerStyle = {
        display: "table",
        position: "relative",
        top: "1.9vw",
        margin: "0 auto",
        fontWeight: "bold",
        fontSize: "30px",
    };

    const subheaderStyle = {
        fontSize: "20px",
    };

    const renderedPlaylists = fetchedData.map((data, index) => {
        if (index === 0 || index % 2 == 0) {
            return (
                <Segment key={index} circular inverted style={square}>
                    <Header style={headerStyle} as="h2" inverted>
                        {data.title}
                        <Header.Subheader style={subheaderStyle}>
                            {data.total}
                        </Header.Subheader>
                    </Header>
                </Segment>
            );
        } else {
            return (
                <Segment key={index} circular style={square}>
                    <Header style={headerStyle} as="h2">
                        {data.title}
                        <Header.Subheader style={subheaderStyle}>
                            {data.total}
                        </Header.Subheader>
                    </Header>
                </Segment>
            );
        }
    });

    return (
        <div>
            <PlaylistModal />
            <div>
                <div className="ui grid">{renderedPlaylists}</div>
            </div>
        </div>
    );
};

export default Playlists;
