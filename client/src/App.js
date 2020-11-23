import React, {Component} from 'react';
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";
import Home from './routes/Home/Home';
import Login from './routes/Auth/Login';
import Signup from './routes/Auth/Signup';
import Navigation from './components/Navigation/Navigation';
import Profile from './routes/Profile/Profile';
import Booking from './routes/Booking/Booking';
import Hotel from './routes/Booking/Hotel';
import QueryResults from './routes/Booking/QueryResults';
import { connect } from 'react-redux';

import './App.scss';

class App extends Component {

  componentDidMount(){

  }

  render(){
    return (
      <Router basename="/backend_war_exploded">
        <div className="App">
          <Navigation />
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route exact path="/booking">
              <Booking />
            </Route>
            <Route exact path="/booking/hotels">
              <QueryResults />
            </Route>
            <Route exact path="/booking/:id">
              <Hotel />
            </Route>
            <Route exact path="/profile">
              <Profile />
            </Route>
            <Route exact path="/login">
              <Login />
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

// first mapStateToProps,
const mapStateToProps = state => {
  return {
    user: state.user,
    isLogged: state.isLogged,
  }
}
// second mapDispatchToProps,

export default connect(mapStateToProps, null)(App);
