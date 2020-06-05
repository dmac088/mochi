import React from 'react';
import { connect } from 'react-redux'
import Dashboard from './Dashboard/Dashboard'
import Auth from '../Login/Auth';

function Account(props) {

    console.log(props);
    const { session } = props;

    return(
      <React.Fragment>
        {(session.tokens.authenticated)
        ? <Dashboard 
            {...props}
            />
        : <Auth />}
      </React.Fragment>
    );
  }

  const mapStateToProps = state => ({
    session: state.session,
  })  


export default connect(mapStateToProps)(Account);