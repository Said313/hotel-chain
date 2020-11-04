import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useForm } from 'react-hook-form'
import axios from 'axios';

import serverPath from '../../api/path';

const Login = ({handleLoginSubmit}) => {

    const history = useHistory();
    const { register, errors, handleSubmit } = useForm();
    const [loginError, setLoginError] = useState("");

    const logIn = formData => {
        axios.post(`${serverPath}/services/auth/login`, {
                login: formData.logLogin,
                password: formData.logPassword,
            })
            .then(res => {
                console.log(res.data);
                console.log(Object.keys(res.data).length);
                if(!(Object.keys(res.data).length === 0)){
                    handleLoginSubmit({
                        user: res.data,
                        isLogged: true,
                    });
                    setLoginError("");
                    history.push('/');
                } else {
                    handleLoginSubmit({
                        user: {},
                        isLogged: false,
                    });
                    setLoginError("Invalid login or password!");
                }
            })
            .catch((error)=>{
                handleLoginSubmit({
                    user: {},
                    isLogged: false,
                })
                window.alert("Cannot access the server!");
                console.log(error);
             });
    }

    return (
        <div className="Login">
            <div>
                <h3>Log in</h3>
                <form onSubmit={handleSubmit(logIn)}>
                    <div>
                        <input 
                            type="text" 
                            className="loginInput"
                            name="logLogin"
                            placeholder="Enter login"
                            required
                            ref={register({required: true})}
                        />
                    </div>
                    <div>
                        <input 
                            type="password" 
                            className="loginInput"
                            name="logPassword"
                            placeholder="Enter password"
                            required
                            minLength="6"
                            ref={register({required: true})}
                        />
                    </div>
                    {loginError && <p style={{color: "red"}}>{loginError}</p>}
                    <button className="loginButton" type="submit">Log in</button>
                    <p className="toSignupText">Do not have an account?</p>
                    <button className="signupButton" onClick={()=>{history.push('/signup')}}>Sign up</button>
                </form>
            </div>
        </div>
    );;
}

export default Login;