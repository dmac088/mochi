import React, {Component} from 'react';

const Greeting = (props) =>{
  console.log('Render Greeting');
    return(
      <div>
        <p></p>
        <p>{ (props.authenticated) ? 'Welcome ' + props.givenNameEn + ' you are logged in!' : 'you are currently logged out!'}</p>
      </div>
    )
};

export default Greeting;
