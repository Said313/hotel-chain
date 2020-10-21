import React from 'react';
import { useHistory } from 'react-router-dom';

const Login = ({state, logIn, handleLogin}) => {

    const history = useHistory();

    const ret = (
        <div className="auth">
            <div>
                <h3>Log in</h3>
                <form onSubmit={(e)=>{
                    e.preventDefault();
                    logIn();
                    history.push('/');
                }}>
                    <div>
                        <label htmlFor="log-login">Login: </label>
                        <input 
                            type="text" 
                            id= "log-login"
                            value={state.log_login}
                            onChange={handleLogin}
                            name="log_login"
                            placeholder="Enter login"
                        />
                    </div>
                    <div>
                        <label htmlFor="log-password">Password: </label>
                        <input 
                            type="password" 
                            id="log-password"
                            value={state.log_password}
                            onChange={handleLogin}
                            name="log_password"
                            placeholder="Enter password"
                        />
                    </div>
                    
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