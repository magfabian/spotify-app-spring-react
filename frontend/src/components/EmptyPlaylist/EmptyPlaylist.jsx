import React from "react";

const EmptyPlaylist = () => {
    return (
        <aside>
            <p>Oops.</p>
            <p>The Playlist is empty</p>
            <a href="/track">Fill playlist with your favorite tracks!</a>
        </aside>
    );
};

export default EmptyPlaylist;
