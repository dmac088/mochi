import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import { isMobile } from '../../services/helpers/ScreenHelper';
import 'velocity-animate/velocity.ui';
import qs from 'query-string';
const $ = window.$;

class CategoryMenuContainer extends Component {

  constructor(props) {
    super(props);
    console.log("constructing");
    this.state = {
        menuVisible: true,
        isMobile: false,
    };
  }

  componentDidMount() {
    this.renderMenu(true);
    window.addEventListener('resize', this.renderMenu);
  }

  renderMenu = (isMounting = false) => {
    if(isMobile() && !this.state.isMobile) { this.setState({isMobile: true, menuVisible: (isMounting) ? false : true}) }
    if(!isMobile() && this.state.isMobile) { this.setState({isMobile: false, menuVisible: true}) }
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
            isMobile={this.state.isMobile}
            history={this.props.history}
          />
        : null)
        }
      </ReactTransitionGroup>
    </div>
  )
}
}


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
    if(event.target.tagName === "I") {return}
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
                  isMobile={this.props.isMobile}
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


class CategoryMenuItem extends Component {

  constructor(props) {
    super(props);
    this.state = {
      hasChildren: this.props.category.childCategoryCount > 0,
      expand: ((this.props.isMobile) ? false : true),
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
                    ((this.state.hasChildren)
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
          <a  id={this.props.category.categoryCode}
              onClick={this.props.changeCategory}
              className={(this.state.hasChildren) ? "megamenu-head" : ""}
              href="shop-left-sidebar.html">
            {this.props.category.categoryDesc}

            {(this.state.hasChildren && this.props.isMobile)
              ? <span>
                  <i onClick={this.expandCat}
                   className={(!this.state.expand)
                              ? "expand menu-expand"
                              : "menu-expand"}>
                  </i>
                </span>
              : null
            }
          </a>
          <ReactTransitionGroup component={React.Fragment}>
            {((this.state.hasChildren && (this.state.expand || !this.props.isMobile))
              ? <CategoryMenuItemSubList
                  renderCategoryListItems={this.props.renderCategoryListItems}
                  children={this.props.category.children}
                  categoryLevel={this.props.category.categoryLevel}
                  changeCategory={this.props.changeCategory}
                  itemCounter={this.props.itemCounter}
                  isMobile={this.props.isMobile}
                />
              : null)}
          </ReactTransitionGroup>
        </li>
    )
  }
}


class CategoryMenuItemSubList extends Component {

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
          className={
            ((this.props.categoryLevel >= 2)
            ? ""
            : "category-mega-menu")
          }>
            {this.props.renderCategoryListItems(this.props.children, false, this.props.changeCategory, this.props.itemCounter)}
      </ul>
    )
  }
}

export default withRouter(CategoryMenuContainer);
