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
      log_login: '',
      log_password: '',
      signInfo: {
        firstname: '',
        lastname: '',
        password: '',
        repeat_password: '',
        id_type: '',
        id_number: '',
        address: '',
        mobile_phone: '',
        home_phone: '',
        category: '',
      },
      signErrors: {
        firstname: '',
        lastname: '',
        password1: '',
        password2: '',
        password3: '',
        repeat_password: '',
        id_type: '',
        id_number: '',
        address: '',
        mobile_phone: '',
        home_phone: '',
      },
    }
  }

  handleLogin = (event) => {
    const {name, value} = event.target;
    let pattern = /[\S]/;

    this.setState({
      [name]: value,
    })
  }

  handleChange = (event) => {
    const {value, name} = event.target.value;
    const {signInfo} = this.state;

    let pattern = /[\S]/
    let passFirstPattern = /[a-zA-Z]/
    let passSecondPattern = /[0-9]/

    let errors = this.state.signErrors;
    switch(name){
      case "password":
        errors.password1 = value.length < 6 ? "Password should contain at least 6 characters and digits." : "";
        errors.password2 = value.match(passFirstPattern) ? "" : "Password should contain at least 1 english letter.";
        errors.password3 = value.match(passSecondPattern) ? "" : "Password should contain at least 1 digit.";
        errors.repeat_password = value !== signInfo.repeat_password ? "Passwords do not match." : "";
        break;
      case "repeat_password":
        errors.repeat_password = value !== signInfo.password ? "Passwords do not match." : "";
        break;
      case "category":
        break;
      default:
        errors[name] = value.match(pattern) ? "" : "This field cannot be empty.";
    }

    this.setState((prevState)=>{
      return {
        signInfo: {
          ...prevState.signInfo,
          [name]: value,
        },
        signErrors: errors,
      }
    })
  }

  logIn = () => {
    axios.post(`${serverPath}/services/auth/login`, {
        login: this.state.log_login,
        password: this.state.log_password,
      })
      .then(res => {
        this.setState({
          user: res.data,
          log_login: '',
          log_password: '',
          isLogged: true,
        })
      })
      .catch((error)=>{
        this.setState({
          log_login: '',
          log_password: '',
          isLogged: false,
        })
        window.alert("Cannot access the server!");
        console.log(error);
      });
  }

  checkSignUpValidity = () => {
    const {signErrors} = this.state;
    let valid = true;
    Object.values(signErrors).forEach(
      (value) => value.length > 0 && (valid = false)
    );

    return valid;
  }

  signUp = () => {
    if(this.checkSignUpValidity()){
      const {signInfo} = this.state;
      axios.post(`${serverPath}/services/auth/signup`, {
        firstname: signInfo.firstname,
        lastname: signInfo.lastname,
        password: signInfo.repeat_password,
        repeat_password: signInfo.repeat_password,
        id_type: signInfo.id_type,
        id_number: signInfo.id_number,
        address: signInfo.address,
        mobile_phone: signInfo.mobile_phone,
        home_phone: signInfo.home_phone,
        category: signInfo.category,
      })
      .catch((error)=>{
        window.alert("Cannot access the server!");
        console.log(error);
      })
      .then((res)=>{
        window.alert("You are signed up successfully!");
      });
    } else {
      window.alert("Not valid Sign Up!");
    }
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
              <Login state={this.state} logIn={this.logIn} handleLogin={this.handleLogin}/>
            </Route>
            <Route exact path="/signup">
              <Signup state={this.state} signUp={this.signUp} handleChange={this.handleChange}/>
            </Route>
          </Switch>

        </div>
      </Router>
    );
  }
}

export default App;
