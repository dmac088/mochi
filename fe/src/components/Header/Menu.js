import React, { Component } from 'react';


class Menu extends Component {


  home = (e) => {
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/' + locale + '/' + currency);
  }


  contact = (e) => {
    e.preventDefault();
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/' + locale + '/' + currency + '/Contact');
  }


  render() {
    return (
      <div className="main-menu">
        <nav>
          <ul>
            <li className="active"><a onClick={this.home} href="#">HOME</a>
            </li>
            <li className="menu-item-has-children">
              <a href="#">Brands</a>
              <ul className="sub-menu mega-menu three-column">
                <li><a href="#">Comvita</a></li>
                <li><a href="#">Airborne</a></li>
                <li><a href="#">Happy Bee</a></li>
                <li><a href="#">Antipodes</a></li>
                <li><a href="#">Wild Ferns</a></li>
                <li><a href="#">Trilogy</a></li>
                <li><a href="#">Anchor</a></li>
              </ul>
            </li>
            <li className="menu-item-has-children">
              <a href="#">BLOG</a>
              <ul className="sub-menu">
                <li><a href="blog-3-column.html">Blog 3 column</a></li>
                <li><a href="blog-grid-left-sidebar.html">Blog Grid Left Sidebar</a></li>
                <li><a href="blog-grid-right-sidebar.html">Blog Grid Right Sidebar</a></li>
                <li><a href="blog-list-left-sidebar.html">Blog List Left Sidebar</a></li>
                <li><a href="blog-list-right-sidebar.html">Blog List Right Sidebar</a></li>
                <li><a href="blog-post-left-sidebar.html">Blog Post Left Sidebar</a></li>
                <li><a href="blog-post-right-sidebar.html">Blog Post Right Sidebar</a></li>
                <li><a href="blog-post-image-format.html">Blog Post Image Format</a></li>
                <li><a href="blog-post-image-gallery.html">Blog Post Image Gallery Format</a></li>
                <li><a href="blog-post-audio-format.html">Blog Post Audio Format</a></li>
                <li><a href="blog-post-video-format.html">Blog Post Video Format</a></li>
              </ul>
            </li>
            <li><a onClick={this.contact} href="#">CONTACT</a></li>
          </ul>
        </nav>
      </div>
    )
  }
}

export default Menu;
