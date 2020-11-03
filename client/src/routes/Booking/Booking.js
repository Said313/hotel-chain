import axios from 'axios';
import React from 'react';
import { useHistory } from 'react-router-dom';
import serverPath from '../../api/path';


const Booking = ({setBookingQuery}) => {
    const history = useHistory();

    const handleSubmit = (event) => {
        event.preventDefault();
        const {target} = event;
        const dest = target.querySelector('.dest').value;
        const startDate = target.querySelector('#startDate').value;
        const endDate = target.querySelector('#endDate').value;
        axios.post(`${serverPath}/services/booking/query`, {
            destination: dest,
            start: startDate,
            end: endDate,
            })
            .then(res => {
                setBookingQuery(res.data);
                history.push('/booking/hotels');
            })
            .catch(error => {
                window.alert("Cannot access the server!");
                console.log(error);
            })
    }

    return (
        <div className="Booking">
            <h3>Find Hotels</h3>
            <form onSubmit={handleSubmit}>
                <input type="text" className="dest" placeholder="Enter destination"/>
                <label>Arrival: </label>
                <input type="date" className="bookingDate" id="startDate" required/>
                <label>Departure: </label>
                <input type="date" className="bookingDate" id="endDate" />
                <div>
                    <button type="submit" className="findHotelsButton">Find hotels</button>
                </div>
            </form>
        </div>
    );
}

export default Booking;