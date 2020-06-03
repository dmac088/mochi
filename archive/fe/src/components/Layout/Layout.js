import React from 'react';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../data/categories/api';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import Scroller from '../Scroller';

export const Layout = withRouter(({history, match, location, ...props}) => {
    const children = React.Children.map(props.children, child => {
     return React.cloneElement(child, {
       categoryList: props.categoryList
     });
   });
    return (
      <React.Fragment>
        <Header
          {...props}/>
          {children}
        <Scroller />
        <Footer />
      </React.Fragment>
    )
  });
