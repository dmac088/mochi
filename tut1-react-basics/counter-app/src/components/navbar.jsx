import React, { Component } from 'react';

//stateless functional component

const NavBar = ({ totalCounters }) => {
  console.log('navBar - Rendered');
  return (
    <nav className="navbar-brand bg-light">
          Navbar <span className="badge badge-pill badge-secondary">{totalCounters}</span>
    
    </nav>
  );
}

export default NavBar
