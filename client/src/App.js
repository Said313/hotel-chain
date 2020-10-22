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
        validfirstname: false,
        lastname: '',
        validlastname: false,
        password: '',
        validpassword: false,
        repeat_password: '',
        validrepeat_password: true,
        id_type: '',
        validid_type: false,
        id_number: '',
        validid_number: false,
        address: '',
        validaddress: false,
        mobile_phone: '',
        validmobile_phone: false,
        home_phone: '',
        validhome_phone: false,
        category: '',
      },
    }
  }

  handleLogin = (event) => {
    const {name, value} = event.target;
    this.setState({
      [name]: value,
    })
  }

  handleChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    const {signInfo} = this.state;

    this.setState((prevState)=>{
      return {
        signInfo: {
          ...prevState.signInfo,
          [name]: value,
        }
      }
    })

    let pattern = /[\S]/
    let passFirstPattern = /[a-zA-Z]/
    let passSecondPattern = /[0-9]/

    if(name !== "category"){
      if(!value.match(pattern)){
        document.querySelector(`#${name}_error`).classList.remove('hide');
        this.setState((prevState)=>{
          return {
            signInfo: {
              ...prevState.signInfo,
              [`valid${name}`]: false,
            }
          }
        })
      } else {
        document.querySelector(`#${name}_error`).classList.add('hide');
        this.setState((prevState)=>{
          return {
            signInfo: {
              ...prevState.signInfo,
              [`valid${name}`]: true,
            }
          }
        })
      }
    }

    let validPass = true;

    if(name === "password"){
      if(value.length < 6){
        document.querySelector(`#password_length_error`).classList.remove('hide');
        validPass = false;
      } else {
        document.querySelector(`#password_length_error`).classList.add('hide');
        validPass = true;
      }
      if(!value.match(passFirstPattern)){
        document.querySelector(`#password_letter_error`).classList.remove('hide');
        validPass = false;
      } else {
        document.querySelector(`#password_letter_error`).classList.add('hide');
        validPass = true;
      }
      if(!value.match(passSecondPattern)){
        document.querySelector(`#password_digit_error`).classList.remove('hide');
        validPass = false;
      } else {
        document.querySelector(`#password_digit_error`).classList.add('hide');
        validPass = true;
      }
    }

    this.setState((prevState)=>{
      return {
        signInfo: {
          ...prevState.signInfo,
          validpassword: true,
        }
      }
    })

    if(name === "repeat_password"){
      if(signInfo.password === value){
        document.querySelector(`#repeat_password_match_error`).classList.add('hide');
      } else {
        document.querySelector(`#repeat_password_match_error`).classList.remove('hide');
      }
    }
  }

  logIn = () => {
    axios.post(`${serverPath}/services/auth/login`, {
        login: this.state.log_login,
        password: this.state.log_password,
      })
      .then(res => {
        this.setState({
          email: '',
          password: '',
          isLogged: true,
        })
      })
      .catch((error)=>{
        this.setState({
          email: '',
          password: '',
          isLogged: false,
        })
        window.alert("Cannot access the server!");
        console.log(error);
      });
  }

  checkSignUpValidity = () => {
    const {
      validfirstname,
    validlastname,
    validpassword,
    validrepeat_password,
    validid_type,
    validid_number,
    validaddress,
    validmobile_phone,
    validhome_phone
    } = this.state.signInfo;

    return (validfirstname &&
    validlastname &&
    validpassword &&
    validrepeat_password &&
    validid_type &&
    validid_number &&
    validaddress &&
    validmobile_phone &&
    validhome_phone
    );
  }

  signUp = (form) => {
      axios.post(`${serverPath}/services/auth/signup`, {
        firstname: form.querySelector('#firstname').value,
        lastname: form.querySelector('#lastname').value,
        password: form.querySelector('#password').value,
        repeat_password: form.querySelector('#repeat_password').value,
        id_type: form.querySelector('#id_type').value,
        id_number: form.querySelector('#id_number').value,
        address: form.querySelector('#address').value,
        mobile_phone: form.querySelector('#mobile_phone').value,
        home_phone: form.querySelector('#home_phone').value,
        category: form.querySelector('#category').value,
      })
      .catch((error)=>{
        window.alert("Cannot access the server!");
        console.log(error);
      })
      .then((res)=>{
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
