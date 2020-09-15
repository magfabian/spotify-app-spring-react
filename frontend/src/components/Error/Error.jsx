import React from "react";

const Error = ({ error }) => {
    return (
        <aside>
            <p>Oops.</p>
            <p>{error.status}</p>
            <p>{error.statusText}</p>
        </aside>
    );
};

export default Error;
