import React from "react";

const Header = () => {
    const headerStyle = {
        backgroundColor: "black",
        position: "absolute",
        top: "0",
        left: "0",
        height: "80px",
        width: "100%"
    };

    const headerName = {
        fontSize: "55px",
        textDecoration: "underline",
        textDecorationColor: "rgb(85,182,96)",
        fontWeight: "bolder",
        position: "absolute",
        left: "20px",
        top: "20px",
        color: "white"
    };

    return (
        <div style={headerStyle}>
            <span style={headerName}>Spotify App</span>
        </div>
    );
};

export default Header;
