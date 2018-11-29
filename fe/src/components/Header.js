
import React from 'react';
import Login from './Login';
import Greeting from './Greeting';

const Header = (props) => {
      return(
      <nav className="navbar navbar-light bg-light justify-content-between">
        <div className="navbar-brand">Navbar</div>
        <div className="form-inline">
            <Greeting
                tokens={props.tokens}
            />
            <Login/>

        </div>
      </nav>
    );
}

export default Header;
