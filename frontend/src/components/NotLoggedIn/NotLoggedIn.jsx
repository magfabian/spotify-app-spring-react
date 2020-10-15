import React from "react";
import { Header } from "semantic-ui-react";
import { NavLink } from "react-router-dom";

const NotLoggedIn = () => {
    const h1Style = {
        fontSize: "40px",
        fontWeight: "bolder",
        marginTop: "20px",
        textDecoration: "underline",
        textDecorationColor: "rgb(85,182,96)",
    };

    const underlineStyle = {
        color: "black",
        textDecoration: "underline",
        textDecorationColor: "rgb(85,182,96)",
    };

    const headerStyle = {
        fontSize: "33px",
        fontWeight: "bolder",
        marginTop: "20px",
    };

    return (
        <aside>
            <Header as="h1" style={h1Style}>
                Your are not logged in!
            </Header>
            <Header as="h2" style={headerStyle}>
                Please{" "}
                <NavLink exact to="/login" style={underlineStyle}>
                    Login
                </NavLink>{" "}
                or{" "}
                <NavLink exact to="/signup" style={underlineStyle}>
                    Sign up
                </NavLink>
                .
            </Header>
        </aside>
    );
};

export default NotLoggedIn;
