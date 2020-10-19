import React, {Fragment} from 'react';
import { useHistory } from 'react-router-dom';
import Navigation from '../../components/Navigation/Navigation';

const Home = ({state, login, logout}) => {

    const history = useHistory();

    const log = () => {
        let ret;

        if(!state.isLogged){
            ret = (
                <Fragment>
                    <button onClick={()=>{history.push('/login')}}>Log in</button>
                    <button onClick={()=>{history.push('/signup')}}>Sign up</button>
                </Fragment>
            );
        } else {
            ret = '';
        }

        return ret;
    }

    return (
        <main>
            <Navigation />
            {log()}
            <h2>This is home page for {state.userType}!</h2>
            <p>{state.isLogged ? "You are logged in." : "Please, log in."}</p>
        </main>
    );
}

export default Home;