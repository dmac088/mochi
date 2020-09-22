import React from 'react';
import { useSelector } from 'react-redux';
import Dashboard from './Dashboard/Dashboard'
import Auth from '../Login/Auth';

function Account(props) {

    const authenticated = useSelector(state => state.session.authenticated);

    return(
      <React.Fragment>
        {(authenticated)
        ? <Dashboard 
            {...props}/>
        : <Auth 
            {...props}/>}
      </React.Fragment>
    );
  }

export default (Account);