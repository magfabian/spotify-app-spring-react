const url = {
    new_releases: "/api/new-releases",
    album_search: "/api/search/albums/",
    playlist_search: "/api/search/playlists/",
    artist_search: "/api/search/artists/",
    track_search: "/api/search/tracks/",
    favorite_track: "/favorite/track",
    favorite_album: "/favorite/album",
    favorite_artist: "/favorite/artist",
    favorite_playlist: "/favorite/playlist",
    favorite_delete_track: "http://localhost:8080/favorite/delete/track",
    favorite_delete_album: "http://localhost:8080/favorite/delete/album",
    favorite_delete_artist: "http://localhost:8080/favorite/delete/artist",
    favorite_delete_playlist: "http://localhost:8080/favorite/delete/playlist",
    favorite_all: "/favorite/get-all",
    playlist: "http://localhost:8080/playlist/",
    playlist_new: "/playlist/new/",
    playlist_get_all: "/playlist/get-all",
    playlist_add_track: "http://localhost:8080/playlist/track/",
    playlist_delete_track: "http://localhost:8080/playlist/delete/track/",
};

export default url;
