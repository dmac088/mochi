import React from 'react';

const Greeting = (props) =>{
    return(
      <div className="mr-sm-2">
        {(props.tokens.authenticated) ? 'Welcome ' + props.tokens.userName + ' you are logged in!' : ''}
      </div>
    );
}

export default Greeting;
