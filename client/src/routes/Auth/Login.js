import React from 'react';
import { useHistory } from 'react-router-dom';
import { useForm } from 'react-hook-form'
import axios from 'axios';

import serverPath from '../../api/path';

const Login = ({handleLoginSubmit}) => {

    const history = useHistory();
    const { register, errors, handleSubmit } = useForm();

    const logIn = formData => {
        axios.post(`${serverPath}/services/auth/login`, {
            login: formData.logLogin,
            password: formData.logPassword,
          })
          .then(res => {
            handleLoginSubmit({
                user: res.data,
                isLogged: true,
            });
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

    const onSubmit = formData => {
        logIn(formData);
    }

    const ret = (
        <div className="auth">
            <div>
                <h3>Log in</h3>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div>
                        <label htmlFor="log-login">Login: </label>
                        <input 
                            type="text" 
                            id= "logLogin"
                            name="logLogin"
                            placeholder="Enter login"
                            ref={register({required: true})}
                        />
                    </div>
                    {errors.logLogin && <p className="validError">This field is required!</p>}
                    <div>
                        <label htmlFor="log-password">Password: </label>
                        <input 
                            type="password" 
                            id="logPassword"
                            name="logPassword"
                            placeholder="Enter password"
                            ref={register({required: true})}
                        />
                    </div>
                    {errors.logPassword && <p className="validError">This field is required!</p>}
                    <button type="submit">Log in</button>
                    <p>Do not have an account?</p>
                    <button onClick={()=>{history.push('/signup')}}>Sign up</button>
                </form>
            </div>
        </div>
    );

    return ret;
}

export default Login;