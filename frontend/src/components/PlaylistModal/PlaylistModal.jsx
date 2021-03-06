import React, { useState } from "react";
import { Button, Form, Modal } from "semantic-ui-react";
import axios from "axios";
import url from "../../utilities/url";

const PlaylistModal = ({ handleClick }) => {
    const [open, setOpen] = useState(false);
    const [input, setInput] = useState("");

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

    const handleNewPlaylist = async () => {
        setOpen(false);
        const playlistName = input.replace(/\s/g, "%20");
        await axios.post(url.playlist_new, playlistName, {
            headers: {
                "Content-Type": "text/plain",
            },
        });
        setInput("");
    };
    return (
        <Modal
            onClose={() => {
                setOpen(false);
            }}
            onOpen={() => setOpen(true)}
            open={open}
            trigger={
                <Button style={buttonStyle} basic color="black">
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
                        onClick={() => {
                            handleNewPlaylist();
                            handleClick();
                        }}
                    >
                        Add
                    </Button>
                </Modal.Actions>
            </Form>
        </Modal>
    );
};

export default PlaylistModal;
