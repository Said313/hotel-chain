import axios from 'axios';
import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { useHistory, withRouter } from 'react-router-dom';
import serverPath from '../../api/path';

import '../Style.scss';

const Hotel = props => {

    const history = useHistory();

    const allHotels = useSelector(state => state.hotelsList);
    const user = useSelector(state => state.user);

    const hotel = allHotels.find(h => h.id === Number(props.match.params.id));
    console.log(hotel);

    const [roomDemand, setRoomDemand] = useState(hotel.roomTypes.map(rT => {return {name: rT.name, number: 0}}));
    const [addServices, setAddServices] = useState([]);

    console.log(roomDemand);

    const setDemand = (target, name) => {
        setRoomDemand(prev => {
            return [
                ...prev.map(d => d.name === name ? {name, number: Number(target.value)} : d)
            ]
        })
    }

    const createBooking = () => {
        axios.post(`${serverPath}/services/booking/create`, {
            hotelName: hotel.name,
            guestId: user.id,
            roomDemand: roomDemand,
            additionalServices: addServices,
        })
            .then(res => {
                window.alert("Booking was created successfully!");
                history.push('/booking');
            })
            .catch(error => {
                window.alert("Cannot access the server!");
                console.log(error);
            })
    }

    return (
        <div className="Hotel">
            <h3>{hotel.name}</h3>
            {hotel.roomTypes.map(rT => <div className="roomType" key={rT.id}>
                <div>
                    {rT.name}
                    <p>{`Capacity: ${rT.capacity}`}</p>
                    <p>{`Size: ${rT.size}`}</p>
                    <p>{`Basic price: ${rT.fixedPrice}KZT`}</p>
                    <p>{`Available rooms: ${rT.rooms.length}`}</p>
                </div>
                <div>
                    <label>Enter number of rooms:</label>
                    <input type="number" min="0" className="roomDemandInput" onChange={(e)=> {setDemand(e.target, rT.name)}}/>
                </div>
                
            </div>)}
            <button className="button" onClick={createBooking}>Book</button>
        </div>
    );
}

export default withRouter(Hotel);