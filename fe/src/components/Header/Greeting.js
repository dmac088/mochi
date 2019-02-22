import React from 'react';

const Greeting = (props) =>{
    return(
      <React.Fragment>
        {(props.authenticated) ? 'Signed in as: ' + props.customer.givenName : ''}
      </React.Fragment>
    );
}

export default Greeting;
