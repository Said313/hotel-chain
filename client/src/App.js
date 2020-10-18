import React, {Component} from 'react';
import { BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";
import Home from './routes/Home/Home';
import Navigation from './components/Navigation/Navigation';

import './App.css';

class App extends Component {

  constructor(){
    super();
    this.state = {
      isLogged: false,
      userType: 'user',
    }
  }

  login = () => {
    this.setState({
      isLogged: true,
      userType: "guest",
    });
  }

  logout = () => {
    this.setState({
      isLogged: false,
      userType: "user",
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
          </Switch>

        </div>
      </Router>
    );
  }
}

export default App;
