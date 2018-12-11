
import React from 'react';
import Greeting from './Greeting';
import Selector from './Selector';
import Search from './Search';

const Header = (props) => {
  //console.log(props.tokens);
      return(
      <nav className="navbar navbar-light bg-light justify-content-between">
        <div className="navbar-brand">Navbar</div>
        <div className="form-inline">
            <Search
              handleSearch={props.handleSearch}
            />
            <Greeting
              tokens={props.tokens}
              customer={props.customer}
            />
            <Selector
              tokens={props.tokens}
              customer={props.customer}
            />

        </div>
      </nav>
    );
}

export default Header;
