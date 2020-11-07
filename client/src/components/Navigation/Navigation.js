import React from 'react';
import {Link, useHistory} from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { useSelector, useDispatch } from 'react-redux';
import { deleteUser, signOut } from '../../actions';

const Navigation = () => {
    const history = useHistory();

    const isLogged = useSelector(state => state.isLogged);
    const dispatch = useDispatch();


    const toProfile = () => {
        history.push('/profile');
    }

    const log = () => {
        if(!isLogged){
            return (
                <div>
                    <button className="navButton" onClick={()=>{history.push('/login')}}>Log in</button>
                    <button className="navButton" onClick={()=>{history.push('/signup')}}>Sign up</button>
                </div>
            );
        } else {
            return (
                <div className="navLoggedIn">
                    <FontAwesomeIcon icon={faUserCircle} className="userIcon" onClick={toProfile}/>
                    <button className="navButton" onClick={()=>{
                        
                        dispatch(signOut());
                        dispatch(deleteUser());

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

                    {isLogged && 
                        <li>
                            <Link to="/profile">Profile</Link>
                        </li>
                    }
                </ul>
                {log()}
            </nav>
        </div>
    );
}

export default Navigation;