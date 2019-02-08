import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import CategoryMenu from './CategoryMenu';
import 'velocity-animate/velocity.ui';
import qs from 'query-string';
const $ = window.$;

const isMobile = () => {
  return  ((window.innerWidth
            || document.documentElement.clientWidth
            || document.body.clientWidth) <= 991);
}

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
    if(isMobile() && this.state.menuVisible) {this.setState({menuVisible: false})}
    else if(!isMobile() && !this.state.menuVisible) {this.setState({menuVisible: true})}
  }

  toggleVisible = () => {
    this.setState(prevState => ({
      menuVisible: !prevState.menuVisible
    }));
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
        {
        ((this.state.menuVisible)
        ? <CategoryMenu
            categoryList={this.props.categoryList}
          />
        : null)
        }
      </ReactTransitionGroup>
    </div>
  )
}
}

export default withRouter(CategoryMenuContainer);
