import React from 'react';
import { BrandSidebarContainer } from './BrandSideBarContainer';

  export const BrandSidebar = (props) => {

    return(
      <BrandSidebarContainer
        {...props}>
        {props.children}
      </BrandSidebarContainer>
    )
  }
