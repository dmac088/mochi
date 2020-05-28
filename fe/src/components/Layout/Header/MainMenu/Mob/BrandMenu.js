import React from 'react';

function BrandMenu() {

    constructor(props) {
        super(props);
    }

    componentWillEnter(callback) {
        if (!this.container) { return }
        slide(this.container, 'slideDown', null, callback);
    }

    componentWillLeave(callback) {
        if (!this.container) { return }
        slide(this.container, 'slideUp', null, callback);
    }

    setContainer = (c) => {
        this.container = c;
    }

    return (
        <ul ref={this.setContainer} className="sub-menu mega-menu three-column" style={{ display: 'block' }}>
            <li><a href="#">Comvita</a></li>
            <li><a href="#">Airborne</a></li>
            <li><a href="#">Happy Bee</a></li>
            <li><a href="#">Antipodes</a></li>
            <li><a href="#">Wild Ferns</a></li>
            <li><a href="#">Trilogy</a></li>
            <li><a href="#">Anchor</a></li>
        </ul>
    )
}

export default BrandMenu;