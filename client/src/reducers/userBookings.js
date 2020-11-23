import * as actions from '../actions/types';

const userBookingsReducer = (state = [], action) => {
    switch(action.type){
        case actions.SET_BOOKINGS_LIST:
            return action.payload;
        case actions.CLEAR_BOOKINGS_LIST:
            return [];
        default:
            return state;
    }
}

export default userBookingsReducer;