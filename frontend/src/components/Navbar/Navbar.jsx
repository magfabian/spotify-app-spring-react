import React from "react";
import NavLinks from "../NavLinks/NavLinks";

const navbarStyle = {
    fontSize: "25px",
};

const Navbar = () => {
    return (
        <div
            style={navbarStyle}
            className="ui secondary vertical pointing menu"
        >
            <NavLinks />
        </div>
    );
};

export default Navbar;
