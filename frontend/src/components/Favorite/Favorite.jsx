import React, { useContext } from "react";
import { FavouriteContext } from "../../context/FavouriteContext";
import { Header, Divider } from "semantic-ui-react";
import CardItem from "../CardItem/CardItem";

const Favorite = () => {
    const { artists, tracks, albums, playlists } = useContext(FavouriteContext);
    const [artist] = artists;
    const [track] = tracks;
    const [album] = albums;
    const [playlist] = playlists;

    const headerStyle = {
        marginBottom: "20px"
    };

    const dividerStlye = {
        maxWidth: "940px"
    };

    const renderCards = (type, cards) => {
        return cards.map(data => (
            <CardItem
                key={data.id}
                category={type}
                imageUrl={data.imageUrl}
                onClickUrl={data.onClickUrl}
                header={data.header}
                footer={data.footer}
                footerUrl={data.footerUrl}
            />
        ));
    };

    const renderedArtists = renderCards("artist", artist);

    const renderedTracks = renderCards("track", track);

    const renderedAlbums = renderCards("albums", album);

    const renderedPlaylist = renderCards("playlist", playlist);

    return (
        <div className='content'>
            <Header style={headerStyle} as='h1'>
                Favorites:
            </Header>
            {artist.length > 0 && (
                <div>
                    <Divider style={dividerStlye} horizontal>
                        ARTISTS
                    </Divider>
                    <div className='ui stackable three column grid'>
                        {renderedArtists}
                    </div>
                </div>
            )}
            {album.length > 0 && (
                <div>
                    <Divider style={dividerStlye} horizontal>
                        ALBUMS
                    </Divider>
                    <div className='ui stackable three column grid'>
                        {renderedAlbums}
                    </div>
                </div>
            )}
            {track.length > 0 && (
                <div>
                    <Divider style={dividerStlye} horizontal>
                        TRACKS
                    </Divider>
                    <div className='ui stackable three column grid'>
                        {renderedTracks}
                    </div>
                </div>
            )}
            {playlist.length > 0 && (
                <div>
                    <Divider style={dividerStlye} horizontal>
                        PLAYLISTS
                    </Divider>
                    <div className='ui stackable three column grid'>
                        {renderedPlaylist}
                    </div>
                </div>
            )}
        </div>
    );
};

export default Favorite;
