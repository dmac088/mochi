import React from 'react';
import { Layout } from './Layout';
import { BreadCrumb } from '../BreadCrumb';

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
    )
  }
