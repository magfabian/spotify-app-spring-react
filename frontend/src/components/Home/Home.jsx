import React from "react";
import url from "../../utilities/url";
import useFetch from "../../utilities/useFetch";
import CardItem from "../CardItem/CardItem";
import Loading from "../Loading/Loading";
import { Header, Divider } from "semantic-ui-react";
import Error from "../Error/Error";

const Home = () => {
    const [status, error, fetchedData] = useFetch(url.new_releases, "release");

    const renderedCards = fetchedData.map(data => (
        <CardItem
            key={data.id}
            category='album'
            img={data.images[0].url}
            imageUrl={data.external_urls.spotify}
            header={data.artists[0].name}
            headerUrl={data.artists[0].external_urls.spotify}
            footer={data.name}
            id={data.id}
        />
    ));

    const headerStyle = {
        marginBottom: "20px"
    };

    const dividerStlye = {
        maxWidth: "940px"
    };

    return (
        <div className='content '>
            {status === "error" && <Error error={error} />}
            {status === "loading" && <Loading />}
            {status === "loaded" && (
                <div>
                    <Header style={headerStyle} as='h1'>
                        New releases:
                    </Header>
                    <Divider style={dividerStlye} horizontal>
                        ALBUMS
                    </Divider>
                    <div className='ui stackable three column grid'>
                        {renderedCards}
                    </div>
                </div>
            )}
        </div>
    );
};

export default Home;
