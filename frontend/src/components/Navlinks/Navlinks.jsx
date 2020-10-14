import React from "react";
import { NavLink } from "react-router-dom";

const NavLinks = () => {
    return (
        <>
            <NavLink activeClassName="active" exact to="/" className="item">
                New releases
            </NavLink>
            <NavLink activeClassName="active" to="/favorite" className="item">
                Favorites
            </NavLink>
            <NavLink
                activeClassName="active"
                exact
                to="/playlists"
                className="item"
            >
                Playlists
            </NavLink>
            <div className="item">
                <p>Search by:</p>
                <div className="menu">
                    <NavLink
                        activeClassName="active"
                        to="/artist"
                        className="item"
                    >
                        Artist
                    </NavLink>
                    <NavLink
                        activeClassName="active"
                        to="/track"
                        className="item"
                    >
                        Track
                    </NavLink>
                    <NavLink
                        activeClassName="active"
                        to="/album"
                        className="item"
                    >
                        Album
                    </NavLink>
                    <NavLink
                        activeClassName="active"
                        to="/playlist"
                        className="item"
                    >
                        Playlist
                    </NavLink>
                    <NavLink
                        activeClassName="active"
                        to="/friend"
                        className="item"
                    >
                        Friend
                    </NavLink>
                </div>
            </div>
        </>
    );
};

export default NavLinks;
