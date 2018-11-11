import React, {Component} from 'react';

const Greeting = (props) =>{
  console.log('Render Greeting');
    return(
      <div>
        <p>Welcome {props.givenNameEn}</p>
        <p>{ (props.isLoggedIn) ? 'You are logged in!' : ''}</p>
      </div>
    )
};

export default Greeting;
