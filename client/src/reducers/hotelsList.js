import * as actions from '../actions/types';

const hotelsListReducer = (state = [], action) => {
    switch(action.type){
        case actions.SET_HOTELS_LIST:
            return action.payload;
        case actions.CLEAR_HOTELS_LIST:
            return [];
        default:
            return state;
    }
}

export default hotelsListReducer;