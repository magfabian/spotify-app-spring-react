import React, { useState, createContext } from "react";

export const ImageUrlContext = createContext();

export const ImageUrlProvider = (props) => {
    const [imageUrl, setImageUrl] = useState("lajos");
    return (
        <ImageUrlContext.Provider value={[imageUrl, setImageUrl]}>
            {props.children}
        </ImageUrlContext.Provider>
    );
};
