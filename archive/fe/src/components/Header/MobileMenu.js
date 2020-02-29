import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { isMobile, slide } from '../../services/helpers/uiHelper';
import { updateParams } from '../../services/helpers/functionHelper';
import { homeRouteString, routeHome, routeContact } from '../../services/helpers/routeHelper';
import ReactTransitionGroup from 'react-addons-transition-group';

class MobileMenu extends Component {

  constructor(props) {
    super(props);
    this.state = {
      revealMenu: false,
      revealBrandMenu: false,
      revealBlogMenu: false,
    };
  }

  toggleMobileMenu = (e) => {
    e.preventDefault();
    this.setState({
      revealMenu: !this.state.revealMenu,
    })
  }

  toggleBrandMenu = (e) => {
    e.preventDefault();
    this.setState({
      revealBrandMenu: !this.state.revealBrandMenu,
    })
  }

  toggleBlogMenu = (e) => {
    e.preventDefault();
    this.setState({
      revealBlogMenu: !this.state.revealBlogMenu,
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
            <ul style={(this.state.revealMenu) ? {display: 'block'} : {display: 'none'}}>
              <li className="active">
                <a href="#">HOME</a>
              </li>
              <li className="menu-item-has-children">
                <a href="#">Brands</a>
                  <ReactTransitionGroup
                        component={React.Fragment}>
                      {(this.state.revealBrandMenu) ? <BrandMenu /> : null}
                  </ReactTransitionGroup>
                <a  onClick={this.toggleBrandMenu}
                    className="mean-expand"
                    href="#"
                    style={{fontSize: 0}}>
                    {(this.state.revealBrandMenu) ? "-" : "+" }
                </a>
              </li>
              <li className="menu-item-has-children">
                <a href="#">BLOG</a>
                  <ReactTransitionGroup
                        component={React.Fragment}>
                      {(this.state.revealBlogMenu) ? <BlogMenu /> : null}
                  </ReactTransitionGroup>
                <a  onClick={this.toggleBlogMenu}
                    className="mean-expand"
                    href="#"
                    style={{fontSize: 0}}>
                    {(this.state.revealBlogMenu) ? "-" : "+" }
                </a>
              </li>
              <li className="mean-last"><a href="#">CONTACT</a></li>
            </ul>
          </nav>
        </div>
      </div>
    )
  }
}


class BrandMenu extends Component {

  constructor(props) {
    super(props);
  }

  componentWillEnter (callback) {
    if(!this.container) { return }
    slide(this.container, 'slideDown', null, callback);
  }

  componentWillLeave (callback) {
    if(!this.container) { return }
    slide(this.container, 'slideUp', null, callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    return (
      <ul ref={this.setContainer} className="sub-menu mega-menu three-column" style={{display: 'block'}}>
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
}

class BlogMenu extends Component {

  constructor(props) {
    super(props);
  }

  componentWillEnter (callback) {
    slide(this.container, 'slideDown', null, callback);
  }

  componentWillLeave (callback) {
    slide(this.container, 'slideUp', null, callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    return (
      <ul ref={this.setContainer} className="sub-menu"
          style={{display: 'block'}}>
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
    )
  }
}

export default withRouter(MobileMenu);
