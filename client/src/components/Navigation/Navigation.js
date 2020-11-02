import React, { Fragment } from 'react';
import {Link, useHistory} from "react-router-dom";

const Navigation = ({state, logOut}) => {
    const history = useHistory();

    const log = () => {
        if(!state.isLogged){
            return (
                <div>
                    <button className="navButton" onClick={()=>{history.push('/login')}}>Log in</button>
                    <button className="navButton" onClick={()=>{history.push('/signup')}}>Sign up</button>
                </div>
            );
        } else {
            return (
                <div>
                    <p>{state.user.firstname}</p>
                    <button onClick={()=>{
                        logOut();
                        history.push('/');
                    }}>Log out</button>
                </div>
            );
        }
    }

    return (
        <div>
            <nav className="navigation">
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="/booking">Booking</Link>
                    </li>
                    <li>
                        <Link to="/profile">Profile</Link>
                    </li>
                </ul>
                {log()}
            </nav>
        </div>
    );
}

export default Navigation;