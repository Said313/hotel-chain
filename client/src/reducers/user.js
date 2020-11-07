import * as actions from '../actions/types';

const userReducer = (state = {}, action) => {
    switch(action.type){
        case actions.SET_USER:
            return {...action.payload}
        case actions.DELETE_USER:
            return {};
        default:
            return state;
    }
}

export default userReducer;