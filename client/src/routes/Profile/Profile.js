import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { useSelector } from 'react-redux';

const Profile = () => {
    const isLogged = useSelector(state => state.isLogged);
    const user = useSelector(state => state.user);
    
    const [pass, setPass] = useState("*".repeat(user.password.length));

    const toggleVisibility = () => {
        if(pass === user.password){
            setPass("*".repeat(user.password.length));
        } else {
            setPass(user.password);
        }
    }

    if(!isLogged){
        return "";
    }

    return (
        <div className="Profile">
            {console.log(user)}
            <FontAwesomeIcon icon={faUserCircle} className="userProfileIcon"/>
            <h3>{`${user.firstname} ${user.lastname}`}</h3>
            <label>Login: </label> {user.login} <br />
            <label>Address: </label> {user.address} <br />
            <label>Mobile phone: </label> {user.mobile_phone} <br />
            <label>Home phone: </label> {user.home_phone} <br />
            <label>Password: </label> {pass} <button onClick={toggleVisibility}>Show</button><br />
        </div>
    );
}

export default Profile;