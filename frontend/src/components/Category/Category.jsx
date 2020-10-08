import React, { useState } from "react";
import { Input } from "semantic-ui-react";
import url from "../../utilities/url";
import { Header } from "semantic-ui-react";
import SearchHandler from "../SearchHandler/SearchHandler";

const Category = ({ categoryType }) => {
    const [search, setSearch] = useState(false);
    const [urlString, setUrl] = useState("");
    const [category, setCategory] = useState("");

    const headerStyle = {
        marginBottom: "20px"
    };

    const handleChange = event => {
        setCategory(categoryType);
        const rawSearch = event.target.value;
        const searchString = rawSearch.replace(/\s/g, " ".charCodeAt());
        if (searchString !== "") {
            switch (categoryType) {
                case "album":
                    setUrl(url.album_search + searchString);
                    setSearch(true);
                    break;
                case "track":
                    setUrl(url.track_search + searchString);
                    setSearch(true);
                    break;
                case "playlist":
                    setUrl(url.playlist_search + searchString);
                    setSearch(true);
                    break;
                default:
                    setUrl(url.artist_search + searchString);
                    setSearch(true);
                    break;
            }
        }
    };

    return (
        <div className='content'>
            <Input
                onChange={handleChange}
                placeholder={"Search " + categoryType}
                size='big'
            />
            <Header style={headerStyle} as='h1'>
                Find your favorite {categoryType}
            </Header>
            {search === true && categoryType === category && (
                <SearchHandler
                    urlString={urlString}
                    categoryType={categoryType}
                />
            )}
        </div>
    );
};

export default Category;
