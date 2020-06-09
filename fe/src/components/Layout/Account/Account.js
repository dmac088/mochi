import React from 'react';
import { useSelector } from 'react-redux'
import Dashboard from './Dashboard/Dashboard'
import Auth from '../Login/Auth';

function Account(props) {
    console.log("rendering account");

    const session = useSelector(state => state.session);

    return(
      <React.Fragment>
        {(session.authenticated)
        ? <Dashboard 
            {...props}
            />
        : <Auth />}
      </React.Fragment>
    );
  }

export default (Account);