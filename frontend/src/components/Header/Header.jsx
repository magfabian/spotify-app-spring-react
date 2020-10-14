import React from "react";
import { NavLink } from "react-router-dom";
import { useState } from "react";
import { useEffect } from "react";
import axios from "axios";

const Header = () => {
    const [loggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        axios
            .get("/user/me")
            .then((response) => {
                setLoggedIn(true);
            })
            .catch((error) => {
                console.log("error");
            });
    }, []);

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

    const handleLogout = () => {
        axios.post("/auth/logout");
    };

    return (
        <div style={headerStyle}>
            <span style={headerName}>Spotify App</span>
            <div style={containerStyle}>
                {loggedIn === true ? (
                    <div>
                        <NavLink exact to="/login" style={navlinkStyle}>
                            Profile
                        </NavLink>
                        <span style={separatorStyle}> | </span>
                        <NavLink style={navlinkStyle} onClick={handleLogout}>
                            Log out
                        </NavLink>
                    </div>
                ) : (
                    <div>
                        <NavLink exact to="/login" style={navlinkStyle}>
                            Login
                        </NavLink>
                        <span style={separatorStyle}> | </span>
                        <NavLink exact to="/signup" style={navlinkStyle}>
                            Sign up
                        </NavLink>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Header;
