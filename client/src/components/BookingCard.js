import React from 'react';

const BookingCard = ({booking: {hotelName, hotelAddress, city, roomNumber, roomTypeName, checkIn, checkOut, bill, name}}) => {
    
    return (
        <div className="BookingCard">
            <h3>{hotelName}</h3>
            <p>{`${city}, ${hotelAddress}`}</p><br />
            <p><b>Check in: </b>{checkIn}</p>
            <p><b>Check out: </b>{checkOut}</p>
            <p><b>Room Type: </b>{roomTypeName}</p>
            <p><b>Room Number: </b>{roomNumber}</p>
        </div>
    );
}

export default BookingCard;