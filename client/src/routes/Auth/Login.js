import React from 'react';
import { useHistory } from 'react-router-dom';

const Login = ({login}) => {

    const history = useHistory();

    const ret = (
        <div className="auth">
            <div>
                <h3>Log in</h3>
                <form onSubmit={(e)=>{
                    e.preventDefault();
                    login(); 
                    history.push('/');
                }}>
                    <div>
                        <label htmlFor="login-email">Email: </label>
                        <input type="text" id= "login-email" placeholder="Enter email"/>
                    </div>

                    <div>
                        <label htmlFor="login-password">Password: </label>
                        <input type="password" id="login-password" placeholder="Enter password"/>
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