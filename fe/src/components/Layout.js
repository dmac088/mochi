import React from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';
import Scroller from './Scroller';
import BreadCrumb from './BreadCrumb';

export const Layout = (props) => {
    return (
      <React.Fragment>
        <Header
          {...props}
        />
        {props.children}
        <Scroller />
        <Footer />
      </React.Fragment>
    );
};

export const LayoutBC = (props) => {
  return(
    <Layout
      {...props}
    >
      <BreadCrumb
        {...props.children.props}
      />
      {props.children}
    </Layout>
  );
};
