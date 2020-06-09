import React from 'react';
import { useSelector } from 'react-redux'
import Dashboard from './Dashboard/Dashboard'
import Auth from '../Login/Auth';

function Account(props) {
    console.log("rendering account");

    const authenticated = useSelector(state => state.session.authenticated);
    console.log(authenticated);

    return(
      <React.Fragment>
        {(authenticated)
        ? <Dashboard 
            {...props}
            />
        : <Auth />}
      </React.Fragment>
    );
  }

export default (Account);