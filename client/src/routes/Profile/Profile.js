import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import {setBookingsList, clearBookingsList} from '../../actions';
import BookingCard from '../../components/BookingCard';

import serverPath from '../../api/path';

const Profile = () => {
    const isLogged = useSelector(state => state.isLogged);
    const user = useSelector(state => state.user);
    const bookings = useSelector(state => state.userBookings);

    const dispatch = useDispatch();

    useEffect(() => {
        axios.post(`${serverPath}/services/profile/getBookings`, {
            id: user.id,
            bookingState: "all",
        })
        .then(res => {
            dispatch(setBookingsList(res.data));
        })
        .catch(error => {
            window.alert("Cannot access the server!");
            console.log(error);
        })
    }, []);
    
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
            <FontAwesomeIcon icon={faUserCircle} className="userProfileIcon"/>
            <h3>{`${user.firstname} ${user.lastname}`}</h3>
            <label>Login: </label> {user.login} <br />
            <label>Address: </label> {user.address} <br />
            <label>Mobile phone: </label> {user.mobile_phone} <br />
            <label>Home phone: </label> {user.home_phone} <br />
            <label>Password: </label> {pass} <button onClick={toggleVisibility}>Show</button><br />
            <h3 className="profileBookingTitle">Bookings</h3>
            <div className="profileBookings">
                {bookings.map(b => <BookingCard key={b.bookingId} booking={b}/>)}
            </div>
        </div>
    );
}

export default Profile;