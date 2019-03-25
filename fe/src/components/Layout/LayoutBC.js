import React, { Component } from 'react';
import Layout from './Layout';
import BreadCrumb from '../BreadCrumb';

class LayoutBC extends Component {

  constructor(props) {
    super(props)
  }

  componentDidMount() {

  }
  
  render() {
    return(
      <Layout
        {...this.props}
      >
        <BreadCrumb
          {...this.props.children.props}
        />
        {this.props.children}
      </Layout>
    )
  }
}

export default LayoutBC;
