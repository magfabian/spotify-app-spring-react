import React from "react";
import { Input } from "semantic-ui-react";
import url from "../../utilities/url";
import { Header } from "semantic-ui-react";

const Friend = () => {
    const headerStyle = {
        marginBottom: "20px",
    };

    const handleChange = (event) => {
        const rawSearch = event.target.value;
        const searchString = rawSearch.replace(/\s/g, " ".charCodeAt());
    };

    return (
        <div className="content">
            <Input
                onChange={handleChange}
                placeholder={"Search between users"}
                size="big"
            />
            <Header style={headerStyle} as="h1">
                Find your friends
            </Header>
        </div>
    );
};

export default Friend;
