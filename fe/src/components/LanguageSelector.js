import React from 'react';
import { Link } from 'react-router-dom';

const LanguageSelector = (props) => {
  return (
    <ul>
      <li onClick={props.changeLang}>
         <Link to="/" id="ENG">ENG</Link>
      </li>
      <li onClick={props.changeLang}>
        <Link to="/" id="HKG">HKG</Link>
      </li>
    </ul>
  );
}

export default LanguageSelector;
