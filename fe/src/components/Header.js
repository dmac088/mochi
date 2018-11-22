
import React from 'react';
import Login from './Login';
import Greeting from './Greeting';

const Header = (props) => {
      return(
      <nav className="navbar navbar-light bg-light justify-content-between">
        <div className="navbar-brand">Navbar</div>
        <div className="form-inline">
            <Greeting
                authenticated={(props.authenticated)}
                username={props.username}
            />
            <Login  authenticated={(props.authenticated)}
                    loginClick={props.loginClick}
                    logoutClick={props.logoutClick}
                    updateCustomerState={props.updateCustomerState}
                    username={props.username}
                    password={props.password}/>

        </div>
      </nav>
    );
}

export default Header;
