import * as actions from './types';

export const signIn = () => ({type: actions.SIGN_IN});

export const signOut = () => ({type: actions.SIGN_OUT});

export const setUser = user => ({type: actions.SET_USER, payload: user});

export const deleteUser = () => ({type: actions.DELETE_USER});

export const setHotelsList = hotelsList => ({type: actions.SET_HOTELS_LIST, payload: hotelsList});

export const clearHotelsList = () => ({type: actions.CLEAR_HOTELS_LIST});