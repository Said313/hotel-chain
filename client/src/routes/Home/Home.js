import React from 'react';
import { useSelector } from 'react-redux';

const Home = () => {

    const isLogged = useSelector(state => state.isLogged);
    const user = useSelector(state => state.user);

    return (
        <main className="Home">
            <div className="HomeMain">
                <h3 className="homeWelcome">Welcome to our Hotel Chain!</h3>
                <p className="homeText">At our Hotel Chain, all-inclusive is synonymous with luxury. Our unique All Inclusive – All Exclusive offering combines all-inclusive advantages with exclusive privileges. 
                    The Hotel Chain offers exceptional escapes that go beyond the imagination to open a new world of horizons for our guests. Our expertise for balancing the ultra-all-inclusive concept with a vibrant, 
                    luxury ambience and family friendly adventures truly defines the Rixos experience. Rixos makes holiday dreams come true for everyone.</p>
            </div>
            <div className="HomeSecond">
                <div className="justBack">
                    <h3 className="homeWelcome">Experience Something New</h3>
                    <p className="homeText">Close to home or across the world, Hilton is there for you with memorable offers and experiences. Check out what’s new.</p>
                </div>
            </div>
        </main>
    );
}

export default Home;