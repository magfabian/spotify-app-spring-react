import React from "react";
import { Header, Divider } from "semantic-ui-react";
import { NavLink } from "react-router-dom";

const EmptyPlaylist = () => {
    const dividerStlye = {
        maxWidth: "940px",
    };

    const headerStyle = {
        fontSize: "30px",
        textDecoration: "underline",
        textDecorationColor: "rgb(85,182,96)",
        fontWeight: "bolder",
        marginTop: "20px",
    };

    return (
        <aside>
            <Divider style={dividerStlye} horizontal>
                TRACKS
            </Divider>
            <Header as="h2">Your playlist is empty.</Header>
            <NavLink exact to="/track">
                <Header as="h3" style={headerStyle}>
                    Fill playlist with your favorite tracks!
                </Header>
            </NavLink>
        </aside>
    );
};

export default EmptyPlaylist;
