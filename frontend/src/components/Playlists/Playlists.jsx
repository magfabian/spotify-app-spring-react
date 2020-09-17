import React, { useState } from "react";
import { Button, Form, Modal } from "semantic-ui-react";
import axios from "axios";
import url from "../../utilities/url";

const Playlists = () => {
    const [open, setOpen] = React.useState(false);
    const [input, setInput] = React.useState("");

    const buttonStyle = {
        fontSize: "20px",
        fontWeight: "bolder",
    };

    const formStyle = {
        fontSize: "20px",
        marginTop: "15px",
        marginBottom: "15px",
    };

    const submitStyle = {
        fontSize: "18px",
        marginBottom: "10px",
        marginLeft: "390px",
    };

    const handleInputChange = (event) => {
        setInput(event.target.value);
    };

    const handleNewPlaylist = () => {
        setOpen(false);
        axios.post(url.playlist_new + input);
        setInput("");
    };

    return (
        <div>
            <Modal
                onClose={() => setOpen(false)}
                onOpen={() => setOpen(true)}
                open={open}
                trigger={
                    <Button
                        style={buttonStyle}
                        basic
                        color="black"
                        onClick={handleNewPlaylist}
                    >
                        Add new playlist
                    </Button>
                }
            >
                <Modal.Header>Add new playlist:</Modal.Header>
                <Form>
                    <Modal.Content>
                        <Form.Field>
                            <input
                                style={formStyle}
                                placeholder="Your playlist name"
                                onChange={handleInputChange}
                            />
                        </Form.Field>
                    </Modal.Content>
                    <Modal.Actions>
                        <Button
                            style={submitStyle}
                            type="submit"
                            onClick={handleNewPlaylist}
                        >
                            Submit
                        </Button>
                    </Modal.Actions>
                </Form>
            </Modal>
        </div>
    );
};

export default Playlists;
