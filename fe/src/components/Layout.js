import React from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';
import Scroller from './Scroller';

const Layout = (props) => {
    return (
      <React.Fragment>
        <Header
          {...props}
          />
          {props.children}
        <Footer />
      </React.Fragment>
    );
};

export default Layout;
