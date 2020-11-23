import React from 'react';
import { useSelector } from 'react-redux';
import HotelCard from '../../components/HotelCard';

const QueryResults = () => {
    const hotelsList = useSelector(state => state.hotelsList);

    return (
        <div className="QueryResults">
            <h3>Hotels Available</h3>
            {hotelsList.map(hotel => {
                return <HotelCard key={hotel.id} hotel={hotel}/>
            })}
        </div>
    );
}

export default QueryResults;