import React from 'react';
import { useSelector } from 'react-redux';

const QueryResults = () => {
    const hotelsList = useSelector(state => state.hotelsList);

    return (
        <div className="QueryResults">
            <h3>Hotels Available</h3>
            {hotelsList.forEach(hotel => {
                return <p>{hotel.name}</p>
            })}
        </div>
    );
}

export default QueryResults;