import * as actions from '../actions/types';

const isLoggedReducer = (state = false, action) => {
    switch(action.type){
        case actions.SIGN_IN:
            return true;
        case actions.SIGN_OUT:
            return false;
        default:
            return state;
    }
}

export default isLoggedReducer;