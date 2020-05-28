import React from 'react';
import ReactTransitionGroup from 'react-addons-transition-group';

function MobileMenu() {

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

export default MobileMenu;
