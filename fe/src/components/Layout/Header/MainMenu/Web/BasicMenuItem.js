import React from 'react';
import { withRouter, Link } from 'react-router-dom';
import {localization} from '../../../Localization/Localization'

export const BasicMenuItem = withRouter(({...props}) => {
    return (
      <BasicMenuItemBase {...props} />
    );
});

export const BasicMenuItemBase = ({...props}) => {
  const { match, routePath, descKey } = props;
  const { lang } = match.params;
  return (
    //<li>
      <Link to={routePath}>
        {localization[lang][descKey]}
      </Link>
    //</li>
  );
};

export default BasicMenuItem;