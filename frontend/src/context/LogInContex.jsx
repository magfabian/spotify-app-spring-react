import React, { useState, createContext } from "react";

export const LogInContext = createContext();

export const LogInProvider = (props) => {
    const [loggedIn, setLoggedIn] = useState("lajos");
    return (
        <LogInContext.Provider value={[loggedIn, setLoggedIn]}>
            {props.children}
        </LogInContext.Provider>
    );
};
