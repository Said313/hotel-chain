import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";

import './App.css';

const App = () => {
  return (
    <Router>
      <div className="App">
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about">Service 2</Link>
            </li>
            <li>
              <Link to="/users">Service 1</Link>
            </li>
          </ul>
        </nav>

        <Switch>

          <Route path="/">
          </Route>

          <Route path="/">
          </Route>

        </Switch>
      </div>
    </Router>
  );
}

export default App;
