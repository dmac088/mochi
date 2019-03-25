import React, { Component } from 'react';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';
import Scroller from '../Scroller';

class Layout extends Component {

  constructor(props) {
    super(props)
    this.state = {
                  "categoryList": [],
                 };
  }

  componentDidMount() {

  }

  render() {
    return (
      <React.Fragment>
        <Header
          {...this.props}
        />
        {this.props.children}
        <Scroller />
        <Footer />
      </React.Fragment>
    )
  }
}


export default Layout;
