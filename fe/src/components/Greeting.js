import React from 'react';

const Greeting = (props) =>{
  console.log('Render Greeting');
    return(
      <div className="mr-sm-2">
        {(props.authenticated) ? 'Welcome ' + props.email + ' you are logged in!' : ''}
      </div>
    );
}

export default Greeting;
