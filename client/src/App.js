import React, {Component} from 'react';
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";
import axios from 'axios';
import Home from './routes/Home/Home';
import Login from './routes/Auth/Login';
import Signup from './routes/Auth/Signup';
import serverPath from './api/path';

import './App.css';

class App extends Component {

  constructor(){
    super();
    this.state = {
      isLogged: false,
      user: {},
    }
  }

  handleLoginSubmit = (data) => {
    this.setState(data);
  }

  logout = () => {
    this.setState({
      user: {},
      isLogged: false,
    });
  }

  render(){
    return (
      <Router>
        <div className="App">
  
          <Switch>
            <Route exact path="/">
              <Home state={this.state} login={this.login} logout={this.logout}/>
            </Route>
            <Route exact path="/service1">
              <Home state={this.state} login={this.login} logout={this.logout}/>
            </Route>
            <Route exact path="/service2">
              <Home state={this.state} login={this.login} logout={this.logout}/>
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
