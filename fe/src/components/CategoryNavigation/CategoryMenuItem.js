import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
import qs from 'query-string';
const $ = window.$;

class CategoryMenuItem extends Component {

  constructor(props) {
    super(props);
    this.state = {
      expandId: this.props.category.categoryId,
      childCategoryCount: this.props.category.childCategoryCount,
      expand: ((isMobile()) ? false : true),
    }
  }

  expandCat = (e) => {
      if(!(e === undefined)) {e.preventDefault()}
      this.setState(prevState => ({
        expand: !prevState.expand,
      }));
  }

  render() {
    return (
        <li
          className={
                    ((this.props.category.childCategoryCount > 0)
                    ? "menu-item-has-children"
                    : "")
                    +
                    ((this.props.isRootList && this.props.itemCounter > 8)
                    ? " hidden"
                    : "")
          }
          style={
            (this.props.isRootList && this.props.itemCounter > 8 && !this.props.showMore)
            ? {"display": "none"}
            : null
          }
          >
          <a id={this.props.category.categoryCode} onClick={this.props.changeCategory} className={(this.props.category.childCategoryCount > 0) ?
               "megamenu-head" : null} href="shop-left-sidebar.html">{this.props.category.categoryDesc}
            {(this.props.category.childCategoryCount > 0 && isMobile())
              ? <i id={this.props.category.categoryId} onClick={this.expandCat} className="expand menu-expand"></i>
              : null
            }
          </a>
          <ReactTransitionGroup>
            {((this.props.category.childCategoryCount > 0 && this.state.expand)
              ? <CategoryMenuItemSubList
                  renderCategoryListItems={this.props.renderCategoryListItems}
                  children={this.props.category.children}
                  changeCategory={this.props.changeCategory}
                  itemCounter={this.props.itemCounter}
                />
              : null)}
          </ReactTransitionGroup>
        </li>
    )
  }
}

export default withRouter(CategoryMenuItem);
