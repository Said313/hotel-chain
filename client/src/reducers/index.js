import isLoggedReducer from './isLogged';
import userReducer from './user';
import hotelsListReducer from './hotelsList';
import { combineReducers } from 'redux';

const all = combineReducers({
    isLogged: isLoggedReducer,
    user: userReducer,
    hotelsList: hotelsListReducer,
});

export default all;