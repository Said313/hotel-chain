import * as actions from './types';

export const signIn = () => ({type: actions.SIGN_IN});

export const signOut = () => ({type: actions.SIGN_OUT});

export const setUser = user => ({type: actions.SET_USER, payload: user});

export const deleteUser = () => ({type: actions.DELETE_USER});

export const setHotelsList = hotelsList => ({type: actions.SET_HOTELS_LIST, payload: hotelsList});

export const clearHotelsList = () => ({type: actions.CLEAR_HOTELS_LIST});

export const setBookingsList = bookingsList => ({type: actions.SET_BOOKINGS_LIST, payload: bookingsList});

export const clearBookingsList = () => ({type: actions.CLEAR_BOOKINGS_LIST});