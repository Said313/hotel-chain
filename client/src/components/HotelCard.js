import React from 'react';
import { useHistory } from 'react-router-dom';

import './Style.scss';

const HotelCard = ({hotel: {name, city, address, description, id}}) => {

    const history = useHistory();

    const toHotelPage = (e) => {
        history.push(`/booking/${id}`);
    }

    return (
        <div className="HotelCard" onClick={toHotelPage}>
            <h3>{name}</h3>
            <p>{`${city}, ${address}`}</p>
            <p>{description}</p>
        </div>
    );
}

export default HotelCard;