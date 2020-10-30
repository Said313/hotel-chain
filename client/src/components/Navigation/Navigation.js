import React, { Fragment } from 'react';
import {Link, useHistory} from "react-router-dom";

const Navigation = ({state, logOut}) => {
    const history = useHistory();

    const log = () => {
        if(!state.isLogged){
            return (
                <Fragment>
                    <button onClick={()=>{history.push('/login')}}>Log in</button>
                    <button onClick={()=>{history.push('/signup')}}>Sign up</button>
                </Fragment>
            );
        } else {
            return (
                <Fragment>
                    <p>{state.user.firstname}</p>
                    <button onClick={()=>{
                        logOut();
                        history.push('/');
                    }}>Log out</button>
                </Fragment>
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
            </nav>
            {log()}
        </div>
    );
}

export default Navigation;