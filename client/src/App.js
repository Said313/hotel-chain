import React, {Component} from 'react';
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";
import axios from 'axios';
import Home from './routes/Home/Home';
import Login from './routes/Auth/Login';
import Signup from './routes/Auth/Signup';
import serverPath from './api/path';
import Navigation from './components/Navigation/Navigation';
import Profile from './routes/Profile/Profile';
import Booking from './routes/Booking/Booking';

import './App.css';


class App extends Component {

  constructor(){
    super();
    this.state = {
      isLogged: false,
      user: {
        type: "user",
      },
    }
  }

  handleLoginSubmit = (data) => {
    this.setState(data);
  }

  logOut = () => {
    this.setState({
      user: {},
      isLogged: false,
    });
  }

  render(){
    return (
      <Router basename="/backend_war_exploded">
        <div className="App">
          <Navigation state={this.state} logOut={this.logOut}/>
          <Switch>
            <Route exact path="/">
              <Home state={this.state} login={this.login} logOut={this.logout}/>
            </Route>
            <Route exact path="/booking">
              <Booking />
            </Route>
            <Route exact path="/profile">
              <Profile />
            </Route>
            <Route exact path="/login">
              <Login handleLoginSubmit={this.handleLoginSubmit}/>
            </Route>
            <Route exact path="/signup">
              <Signup />
            </Route>
          </Switch>

        </div>
      </Router>
    );
  }
}

export default App;
