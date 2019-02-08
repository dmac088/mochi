import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
import qs from 'query-string';
const $ = window.$;

class CategoryMenuItemSubList extends Component {

  constructor(props) {
    super(props);
  }

  componentWillEnter (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideDown', { duration: 1000 , "display":""}).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    return (
      <ul ref={this.setContainer}
          className="category-mega-menu">
            {this.props.renderCategoryListItems(this.props.children, false, this.props.changeCategory, this.props.itemCounter)}
      </ul>
    )
  }
}

export default withRouter(CategoryMenuItemSubList);
