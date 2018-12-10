
import React from 'react';
import Greeting from './Greeting';
import Selector from './Selector';
import Search from './Search';

const Header = (props) => {
  //console.log(props);
      return(
      <nav className="navbar navbar-light bg-light justify-content-between">
        <div className="navbar-brand">Navbar</div>
        <div className="form-inline">
            <Search/>
            <Greeting
              tokens={props.tokens}
              customer={props.customer}
            ></Greeting>

            <Selector>
                tokens={props.tokens}
                customer={props.customer}
            </Selector>

        </div>
      </nav>
    );
}

export default Header;
