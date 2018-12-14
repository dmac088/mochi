import React from 'react';

const Greeting = (props) =>{
    return(
      <div className="mr-sm-2">
        {(props.authenticated) ? 'Welcome ' + props.customer.givenName + ' you are logged in!' : ''}
      </div>
    );
}

export default Greeting;
