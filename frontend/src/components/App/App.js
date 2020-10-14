import React from "react";
import Header from "../Header/Header";
import Home from "../Home/Home";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Navbar from "../Navbar/Navbar";
import Favorite from "../Favorite/Favorite";
import Category from "../Category/Category";
import "./App.css";
import Playlists from "../Playlists/Playlists";
import Playlist from "../Playlist/Playlist";
import Login from "../Login/Login";
import Signup from "../Signup/Signup";
import Friend from "../Friend/Friend";
import { LogInProvider } from "../../context/LogInContex";

function App() {
    return (
        <LogInProvider>
            <Router>
                <Header />
                <div className="wrapper">
                    <Navbar />
                    <Switch>
                        <Route exact path="/">
                            <Home />
                        </Route>
                        <Route path="/favorite">
                            <Favorite />
                        </Route>
                        <Route exact path="/playlists">
                            <Playlists />
                        </Route>
                        <Route path="/artist">
                            <Category categoryType={"artist"} />
                        </Route>
                        <Route path="/album">
                            <Category categoryType={"album"} />
                        </Route>
                        <Route path="/track">
                            <Category categoryType={"track"} />
                        </Route>
                        <Route path="/playlist">
                            <Category categoryType={"playlist"} />
                        </Route>
                        <Route path="/login">
                            <Login />
                        </Route>
                        <Route path="/signup">
                            <Signup />
                        </Route>
                        <Route path="/friend">
                            <Friend />
                        </Route>
                        <Route
                            path="/playlists/:title"
                            render={(props) => <Playlist {...props} />}
                        ></Route>
                    </Switch>
                </div>
            </Router>
        </LogInProvider>
    );
}

export default App;
