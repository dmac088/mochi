import React from 'react';

const LanguageSelector = (props) => {
  return (
    <ul>
      <li onClick={props.changeLang}>
         <a id="ENG" href="#">ENG</a>
      </li>
      <li onClick={props.changeLang}>
        <a id="HKG" href="#">HKG</a>
      </li>
    </ul>
  );
}

export default LanguageSelector;
