import React from 'react';
import Navigation from '../../components/Navigation/Navigation';

const Home = ({state}) => {

    return (
        <main className="Home">
            <h2 className="homeMainText">This is home page for {state.isLogged ? state.user.firstname : "guest"}!</h2>
            <h3 className="homeWelcome">Welcome to our Hotel Chain!</h3>
            <p className="homeText">At our Hotel Chain, all-inclusive is synonymous with luxury. Our unique All Inclusive – All Exclusive offering combines all-inclusive advantages with exclusive privileges. 
                The Hotel Chain offers exceptional escapes that go beyond the imagination to open a new world of horizons for our guests. Our expertise for balancing the ultra-all-inclusive concept with a vibrant, 
                luxury ambience and family friendly adventures truly defines the Rixos experience. Rixos makes holiday dreams come true for everyone.</p>
        </main>
    );
}

export default Home;