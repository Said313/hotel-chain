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
import QueryResults from './routes/Booking/QueryResults';

import './App.css';


class App extends Component {

  constructor(){
    super();
    this.state = {
      isLogged: false,
      user: {
        type: "user",
      },
      hotelsList: [],
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

  setBookingQuery = (hotels) => {
    this.setState({
      hotelsList: hotels,
    })
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
              <Booking setBookingQuery={this.setBookingQuery}/>
            </Route>
            <Route exact path="/booking/hotels">
              <QueryResults state={this.state}/>
            </Route>
            <Route exact path="/profile">
              <Profile state={this.state}/>
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
