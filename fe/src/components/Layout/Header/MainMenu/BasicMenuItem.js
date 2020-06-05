import React from 'react';
import { Link } from 'react-router-dom';
import {localization} from '../../Localization/Localization'

export const BasicMenuItem = (props) => {
  const { match, routePath, descKey } = props;
  const { lang } = match.params;
  return (
      <Link to={routePath}>
        {localization[lang][descKey]}
      </Link>
  );
};

export default BasicMenuItem;