import React, { Component } from 'react';


class MobileMenu extends Component {

  constructor(props) {
    super(props);
    this.state = { revealMenu: false };
  }

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

  toggleMobileMenu = (e) => {
    e.preventDefault();
    this.setState({
      revealMenu: !this.state.revealMenu,
    })
  }


  render() {
    return (
      <div className="mobile-menu d-block d-lg-none mean-container">
        <div className="mean-bar">
          <a  onClick={this.toggleMobileMenu}
              href="#nav"
              className={"meanmenu-reveal " + ((this.state.revealMenu) ? "meanclose" : "")}
              style={{background: '',
                      color: '',
                      right: 0,
                      left: 'auto'}}>

          <span className={(this.state.revealMenu) ? "menu-close" : "menu-bar"} />
          </a>
          <nav className="mean-nav">
            <ul style={ (this.state.revealMenu) ? {display: 'block'} : {display: 'none'}}>
              <li className="active"><a href="#">HOME</a></li>
              <li className="menu-item-has-children">
                <a href="#">Brands</a>
                <ul className="sub-menu mega-menu three-column" style={{display: 'none'}}>
                  <li><a href="#">Comvita</a></li>
                  <li><a href="#">Airborne</a></li>
                  <li><a href="#">Happy Bee</a></li>
                  <li><a href="#">Antipodes</a></li>
                  <li><a href="#">Wild Ferns</a></li>
                  <li><a href="#">Trilogy</a></li>
                  <li><a href="#">Anchor</a></li>
                </ul>
                <a className="mean-expand" href="#" style={{fontSize: 0}}>+</a>
              </li>
              <li className="menu-item-has-children">
                <a href="#">BLOG</a>
                <ul className="sub-menu" style={{display: 'none'}}>
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
                <a className="mean-expand" href="#" style={{fontSize: 0}}>+</a>
              </li>
              <li className="mean-last"><a href="#">CONTACT</a></li>
            </ul>
          </nav>
        </div>
      </div>
    )
  }
}

export default MobileMenu;
