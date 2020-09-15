import React from "react";

const Footer = () => {
    const footerName = {
        fontSize: "20px",
        fontWeight: "bolder",
        color: "white"
    };

    return (
        <div className='footer'>
            <p style={footerName}>2020 - Club Mates</p>
        </div>
    );
};

export default Footer;
