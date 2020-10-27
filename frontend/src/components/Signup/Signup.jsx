import React, { useState } from "react";
import {
    Button,
    Form,
    Grid,
    Header,
    Segment,
    Image,
    List,
} from "semantic-ui-react";
import { Link } from "react-router-dom";
import axios from "axios";
import url from "../../utilities/url";

const Signup = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");
    const [imageUrl, setImageUrl] = useState("");

    const changeFirstName = (event) => {
        setFirstName(event.target.value);
    };

    const changeLastName = (event) => {
        setLastName(event.target.value);
    };

    const changeEmail = (event) => {
        setEmail(event.target.value);
    };

    const changePassword = (event) => {
        setPassword(event.target.value);
    };

    const changeUsername = (event) => {
        setUsername(event.target.value);
    };

    const headerStyle = {
        fontWeight: "bolder",
        marginBottom: "10px",
    };

    const avatarStyle = {
        marginBottom: "20px",
    };

    const selectImage = (event) => {
        setImageUrl(event.target.dataset.url);
    };

    const handleSignUp = () => {
        const user = {
            userName: username,
            password: password,
            fullName: firstName + " " + lastName,
            emailAddress: email,
            imageUrl: imageUrl,
        };
        axios.post(url.signup, user);
    };

    return (
        <Grid
            textAlign="center"
            style={{ height: "200vh" }}
            verticalAlign="top"
        >
            <Grid.Column style={{ maxWidth: 530 }}>
                <Header as="h2" color="rgb(85,182,96)" textAlign="center">
                    Sign up
                </Header>
                <Form size="large">
                    <Segment stacked>
                        <span style={headerStyle}>Choose your avatar: </span>
                        <div stlye={avatarStyle}>
                            <List selection horizontal relaxed>
                                <List.Item>
                                    <Image
                                        avatar
                                        onClick={selectImage}
                                        size="tiny"
                                        style={
                                            imageUrl.includes("steve")
                                                ? {
                                                      border:
                                                          "4px solid rgb(85,182,96)",
                                                  }
                                                : {}
                                        }
                                        data-url="https://semantic-ui.com/images/avatar/large/steve.jpg"
                                        src="https://semantic-ui.com/images/avatar/large/steve.jpg"
                                    />
                                </List.Item>
                                <List.Item>
                                    <Image
                                        avatar
                                        onClick={selectImage}
                                        size="tiny"
                                        style={
                                            imageUrl.includes("ade")
                                                ? {
                                                      border:
                                                          "4px solid rgb(85,182,96)",
                                                  }
                                                : {}
                                        }
                                        data-url="https://semantic-ui.com/images/avatar/large/ade.jpg"
                                        src="https://semantic-ui.com/images/avatar/large/ade.jpg"
                                    />
                                </List.Item>
                                <List.Item>
                                    <Image
                                        avatar
                                        onClick={selectImage}
                                        size="tiny"
                                        style={
                                            imageUrl.includes("chris")
                                                ? {
                                                      border:
                                                          "4px solid rgb(85,182,96)",
                                                  }
                                                : {}
                                        }
                                        data-url="https://semantic-ui.com/images/avatar/large/chris.jpg"
                                        src="https://semantic-ui.com/images/avatar/large/chris.jpg"
                                    />
                                </List.Item>
                                <List.Item>
                                    <Image
                                        avatar
                                        onClick={selectImage}
                                        size="tiny"
                                        style={
                                            imageUrl.includes("helen")
                                                ? {
                                                      border:
                                                          "4px solid rgb(85,182,96)",
                                                  }
                                                : {}
                                        }
                                        data-url="https://semantic-ui.com/images/avatar/large/helen.jpg"
                                        src="https://semantic-ui.com/images/avatar/large/helen.jpg"
                                    />
                                </List.Item>
                            </List>
                        </div>
                        <Form.Group widths={2}>
                            <Form.Input
                                label="First name"
                                placeholder="First name"
                                onChange={changeFirstName}
                            />
                            <Form.Input
                                label="Last name"
                                placeholder="Last name"
                                onChange={changeLastName}
                            />
                        </Form.Group>
                        <Form.Input
                            fluid
                            icon="mail"
                            iconPosition="left"
                            placeholder="E-mail address"
                            onChange={changeEmail}
                        />
                        <Form.Input
                            fluid
                            icon="user"
                            iconPosition="left"
                            placeholder="Username"
                            onChange={changeUsername}
                        />
                        <Form.Input
                            fluid
                            icon="lock"
                            iconPosition="left"
                            placeholder="Password"
                            type="password"
                            onChange={changePassword}
                        />
                        <Button
                            fluid
                            size="large"
                            as={Link}
                            to="/login"
                            onClick={handleSignUp}
                        >
                            Create account
                        </Button>
                    </Segment>
                </Form>
            </Grid.Column>
        </Grid>
    );
};

export default Signup;
