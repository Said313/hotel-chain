import React, {Fragment} from 'react';
import Navigation from '../../components/Navigation/Navigation';

const Home = ({state, login, logout}) => {

    const log = () => {
        let ret;

        if(!state.isLogged){
            ret = (
                <Fragment>
                    <button onClick={login}>Log in</button>
                    <button>Sign in</button>
                </Fragment>
            );
        } else {
            ret = (
                <Fragment>
                    <button onClick={logout}>Log out</button>
                </Fragment>
            );
        }

        return ret;
    }

    return (
        <main>
            <Navigation />
            <h2>This is home page for {state.userType}!</h2>
            <p>{state.isLogged ? "You are logged in." : "Please, log in."}</p>
            {log()}
        </main>
    );
}

export default Home;