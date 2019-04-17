import React from 'react';
import { BrandSideBarContainer } from './BrandSideBarContainer';

  export const BrandSidebar = (props) => {
    console.log(props)
    return(
      <BrandSideBarContainer
        {...props}>
        {props.children}
      </BrandSideBarContainer>
    )
  }
