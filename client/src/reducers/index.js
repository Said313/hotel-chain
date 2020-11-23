import isLoggedReducer from './isLogged';
import userReducer from './user';
import hotelsListReducer from './hotelsList';
import userBookingsList from './userBookings';
import { combineReducers } from 'redux';

const all = combineReducers({
    isLogged: isLoggedReducer,
    user: userReducer,
    hotelsList: hotelsListReducer,
    userBookings: userBookingsList,
});

export default all;