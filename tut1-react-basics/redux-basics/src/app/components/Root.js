import React from 'react';
import { Header } from './Header';

export class Root extends React.Component {
  render() {
    return(
      <div className='Container'>
        <div className='row'>
          <div className='col-xs-10 col-xs-offset'>
            <Header />
          </div>
        </div>
        <div className='row'>
          <div className='col-xs-10 col-xs-offset'>
            {this.props.children}
          </div>
        </div>
      </div>
    );
  }
}
