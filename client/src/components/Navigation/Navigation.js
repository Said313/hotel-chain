import React from 'react';
import {Link} from "react-router-dom";

const Navigation = () => {
    return (
        <nav className="navigation">
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/service1">Service 1</Link>
                </li>
                <li>
                    <Link to="/service2">Service 2</Link>
                </li>
            </ul>
        </nav>
    );
}

export default Navigation;