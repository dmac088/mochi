import React from 'react';

const Greeting = (props) =>{
    return(
      <React.Fragment>
        {(props.authenticated) ? 'Welcome ' + props.customer.givenName + ' you are logged in!' : ''}
      </React.Fragment>
    );
}

export default Greeting;
