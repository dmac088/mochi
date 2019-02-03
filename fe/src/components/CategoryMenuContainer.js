
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
const $ = window.$;


class CategoryMenuContainer extends Component {


  constructor(props) {
    super(props);
    this.state = {
        menuVisible: true,
    };
  }

  componentDidMount() {
    this.renderMenu();
    window.addEventListener('resize', this.renderMenu);
  }

  renderMenu = () => {
    if(this.getSize() <= 991 && this.state.menuVisible === true) {this.setState({menuVisible: false})}
    else if(this.getSize() > 991 && this.state.menuVisible === false) {this.setState({menuVisible: true})}
  }

  toggleVisible = () => {
    this.setState(prevState => ({
      menuVisible: !prevState.menuVisible
    }));
  }

  getSize = () => {
    return  window.innerWidth
              || document.documentElement.clientWidth
              || document.body.clientWidth;
  }

render() {
  return (
    <div className="hero-side-category">
      {/* Category Toggle Wrap */}
      <div className="category-toggle-wrap">
        {/* Category Toggle */}
        <button onClick={this.toggleVisible} className="category-toggle"> <span className="arrow_carrot-right_alt2 mr-2" /> All Categories</button>
      </div>
      {/* Category Menu */}
      <ReactTransitionGroup component="nav" className="category-menu">
        { this.state.menuVisible ? <CategoryMenu/> : null }
      </ReactTransitionGroup>
    </div>
  )
}
}


class CategoryMenu extends Component {

  constructor(props) {
    super(props);
  }

  componentWillEnter (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return;}
    Velocity(element, 'slideDown', { duration: 1000 }).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return;}
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    return(
      <ul ref={this.setContainer}>
        <li><a href="shop-left-sidebar.html">Vagatables</a></li>
        <li className="menu-item-has-children"><a href="shop-left-sidebar.html">Salad</a>
          {/* Mega Category Menu Start */}
          <ul className="category-mega-menu">
            <li className="menu-item-has-children">
              <a className="megamenu-head" href="shop-left-sidebar.html">Vegetables</a>
              <ul>
                <li><a href="shop-left-sidebar.html">Salad</a></li>
                <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                <li><a href="shop-left-sidebar.html">Fruits</a></li>
                <li><a href="shop-left-sidebar.html">Peanuts</a></li>
              </ul>
            </li>
            <li className="menu-item-has-children">
              <a className="megamenu-head" href="shop-left-sidebar.html">Fast Foods</a>
              <ul>
                <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                <li><a href="shop-left-sidebar.html">Fruit</a></li>
                <li><a href="shop-left-sidebar.html">Butter</a></li>
              </ul>
            </li>
            <li className="menu-item-has-children">
              <a className="megamenu-head" href="shop-left-sidebar.html">Salad</a>
              <ul>
                <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                <li><a href="shop-left-sidebar.html">Salad</a></li>
                <li><a href="shop-left-sidebar.html">Peanuts</a></li>
              </ul>
            </li>
          </ul>{/* Mega Category Menu End */}
        </li>
        <li className="menu-item-has-children"><a href="shop-left-sidebar.html">Fast Foods</a>
          {/* Mega Category Menu Start */}
          <ul className="category-mega-menu">
            <li className="menu-item-has-children">
              <a className="megamenu-head" href="shop-left-sidebar.html">Vegetables</a>
              <ul>
                <li><a href="shop-left-sidebar.html">Salad</a></li>
                <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                <li><a href="shop-left-sidebar.html">Fruits</a></li>
                <li><a href="shop-left-sidebar.html">Peanuts</a></li>
              </ul>
            </li>
            <li className="menu-item-has-children">
              <a className="megamenu-head" href="shop-left-sidebar.html">Fast Foods</a>
              <ul>
                <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                <li><a href="shop-left-sidebar.html">Fruit</a></li>
                <li><a href="shop-left-sidebar.html">Butter</a></li>
              </ul>
            </li>
            <li className="menu-item-has-children">
              <a className="megamenu-head" href="shop-left-sidebar.html">Salad</a>
              <ul>
                <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                <li><a href="shop-left-sidebar.html">Salad</a></li>
                <li><a href="shop-left-sidebar.html">Peanuts</a></li>
              </ul>
            </li>
          </ul>{/* Mega Category Menu End */}
        </li>
        <li><a href="shop-left-sidebar.html">Beans</a></li>
        <li><a href="shop-left-sidebar.html">Bread</a></li>
        <li><a href="shop-left-sidebar.html">Fish &amp; Meats</a></li>
        <li><a href="shop-left-sidebar.html">Peanuts</a></li>
        <li><a href="shop-left-sidebar.html">Birds</a></li>
        <li className="hidden"><a href="shop-left-sidebar.html">Eggs</a></li>
        <li className="hidden"><a href="shop-left-sidebar.html">Fruits</a></li>
        <li><a href="#" id="more-btn"><span className="icon_plus_alt2" /> More Categories</a></li>
      </ul>
    )
  }
}

export default CategoryMenuContainer;
