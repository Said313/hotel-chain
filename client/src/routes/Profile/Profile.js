import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';

const Profile = ({state : {user, isLogged}}) => {
    const [pass, setPass] = useState("**********");

    const toggleVisibility = () => {
        if(pass === user.password){
            setPass("**********");
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