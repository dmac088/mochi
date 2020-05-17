import React from "react";
import { Link } from 'react-router-dom';

export const Language = () => {
    return (
        <li> <a href="#">English <i className="fa fa-chevron-down"></i></a>
            <ul>
                <li><a>French</a></li>
                <li><a>Japanease</a></li>
            </ul>
        </li>
    );
}

export default Language;






