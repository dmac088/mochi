import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
import qs from 'query-string';
const $ = window.$;

class CategoryMenu extends Component {

  constructor(props) {
    super(props);
    this.state = {
      showMore: false,
    }
  }

  componentWillEnter (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideDown', { duration: 1000 }).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  changeCategory = (event) => {
    //get the query parameters
    event.preventDefault();
    let urlParams = (qs.parse(this.props.history.location.search));
    let mergedParams = Object.assign(urlParams, {
                                                  category: event.target.id,
                                                  page: 0
                                                });
    const searchString = qs.stringify(mergedParams);
    this.props.history.push({
      "pathname": '/Search',
      "search": searchString,
    });
  }

  setContainer = (c) => {
    this.container = c;
  }

  showMore = () => {
    this.setState({
      showMore: true,
    })
  }

  showLess = () => {
    this.setState({
      showMore: false,
    })
  }

  renderCategoryListItems = (categoryList, isRootList, changeCategory, itemCounter) => {
    return categoryList.map(category => {
        if(isRootList) {itemCounter+=1};
      return(
            <ReactTransitionGroup
                  key={category.categoryId}
                  component={CategoryMenuItem}
                  category={category}
                  renderCategoryListItems={this.renderCategoryListItems}
                  isRootList={isRootList}
                  changeCategory={changeCategory}
                  itemCounter={itemCounter}
                  showMore={this.state.showMore}>
            </ReactTransitionGroup>
      )
    });
  }

  render() {
    return(
      <ul ref={this.setContainer}>
        {this.renderCategoryListItems(this.props.categoryList, true, this.changeCategory, 0)}
        {
          ((this.props.categoryList.length > 8 && !this.state.showMore)
          ? <li><a onClick={this.showMore} href="#" id="more-btn"><span className="icon_plus_alt2" /> More Categories</a></li>
          : <li><a onClick={this.showLess} href="#" id="less-btn"><span className="icon_minus_alt2" /> Less Categories</a></li>)
        }
      </ul>
    )
  }
}

export default withRouter(CategoryMenu);
