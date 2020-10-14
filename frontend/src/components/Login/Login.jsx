import React, { useState } from "react";
import {
    Button,
    Form,
    Grid,
    Header,
    Image,
    Message,
    Segment,
} from "semantic-ui-react";
import { NavLink } from "react-router-dom";
import axios from "axios";
import url from "../../utilities/url";

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const signUpStyle = {
        color: "rgb(85,182,96)",
    };

    const changeEmail = (event) => {
        setEmail(event.target.value);
    };

    const changePassword = (event) => {
        setPassword(event.target.value);
    };

    const handleLogin = () => {
        const user = {
            emailAddress: email,
            password: password,
        };
        axios
            .post(url.login, user, {
                withCredentials: true,
            })
            .then((response) => {})
            .catch((error) => {
                setError("error");
            });
    };
    return (
        <Grid
            textAlign="center"
            style={{ height: "100vh" }}
            verticalAlign="top"
        >
            <Grid.Column style={{ maxWidth: 450 }}>
                <Header as="h1" color="rgb(85,182,96)" textAlign="center">
                    Login to your account
                </Header>
                {error === "error" && (
                    <Header as="h3" color="red" textAlign="center">
                        Wrong username or password!
                    </Header>
                )}
                <Form size="large">
                    <Segment stacked>
                        <Form.Input
                            fluid
                            icon="user"
                            iconPosition="left"
                            placeholder="E-mail address"
                            onChange={changeEmail}
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
                            color="rgb(85,182,96)"
                            fluid
                            size="large"
                            onClick={handleLogin}
                        >
                            Login
                        </Button>
                    </Segment>
                </Form>
                <Message>
                    New to us?{" "}
                    <NavLink exact to="/signup" style={signUpStyle}>
                        Sign Up
                    </NavLink>
                </Message>
            </Grid.Column>
        </Grid>
    );
};

export default Login;
