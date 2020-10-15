import React, { useState } from "react";
import { Button, Form, Grid, Header, Segment } from "semantic-ui-react";
import { Link } from "react-router-dom";
import axios from "axios";
import url from "../../utilities/url";

const Signup = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");

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

    const handleSignUp = () => {
        const user = {
            userName: username,
            password: password,
            fullName: firstName + " " + lastName,
            emailAddress: email,
        };
        axios.post(url.signup, user);
    };

    return (
        <Grid
            textAlign="center"
            style={{ height: "100vh" }}
            verticalAlign="top"
        >
            <Grid.Column style={{ maxWidth: 450 }}>
                <Header as="h2" color="rgb(85,182,96)" textAlign="center">
                    Sign up
                </Header>
                <Form size="large">
                    <Segment stacked>
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
