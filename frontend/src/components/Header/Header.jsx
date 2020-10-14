import React from "react";
import { NavLink } from "react-router-dom";

const Header = () => {
    const headerStyle = {
        backgroundColor: "black",
        position: "absolute",
        top: "0",
        left: "0",
        height: "80px",
        width: "100%",
    };

    const headerName = {
        fontSize: "55px",
        textDecoration: "underline",
        textDecorationColor: "rgb(85,182,96)",
        fontWeight: "bolder",
        position: "absolute",
        left: "20px",
        top: "20px",
        color: "white",
    };

    const navlinkStyle = {
        color: "white",
        fontSize: "30px",
        fontWeight: "bolder",
        textDecoration: "underline",
        textDecorationColor: "rgb(85,182,96)",
    };

    const separatorStyle = {
        color: "white",
        fontSize: "30px",
        fontWeight: "bolder",
    };

    const containerStyle = {
        position: "absolute",
        right: "10px",
        top: "20px",
    };

    return (
        <div style={headerStyle}>
            <span style={headerName}>Spotify App</span>
            <div style={containerStyle}>
                <NavLink exact to="/login" style={navlinkStyle}>
                    Login
                </NavLink>
                <span style={separatorStyle}> | </span>
                <NavLink exact to="/signup" style={navlinkStyle}>
                    Sign up
                </NavLink>
            </div>
        </div>
    );
};

export default Header;
