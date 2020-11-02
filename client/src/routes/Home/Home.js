import React from 'react';
import Navigation from '../../components/Navigation/Navigation';

const Home = ({state}) => {

    return (
        <main className="Home">
            <h2>This is home page for {state.user.type}!</h2>
            <p>{state.isLogged ? "You are logged in." : "Please, log in."}</p>
        </main>
    );
}

export default Home;