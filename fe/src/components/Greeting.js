import React, {Component} from 'react';

const Greeting = (props) =>{
  console.log('Render Greeting');
    return(
      <div className="mr-sm-2">
        {(props.authenticated) ? 'Welcome ' + props.givenNameEn + ' you are logged in!' : ''}
      </div>
    );
}

export default Greeting;
