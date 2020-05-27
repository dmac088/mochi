import React from 'react';

function CategoryMenuItem(props) {
    const { categoryDesc } = props;
    
    return (
        <li><a href="#">{categoryDesc}</a></li>
    );
}

export default CategoryMenuItem;