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
    window.addEventListener('resize', this.renderMenu , {
      passive: true
    });
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
    Velocity(element, 'slideDown', { duration: 500 }).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideUp', { duration: 500 }).then(callback);
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

  getIndent = (level, offset = 0) => {
    return ((level === 1)
          ? 25
          : 25 + (((level-1) * 10)-offset)) + "px";
  }

  render() {
    let catlvl = this.props.category.categoryLevel;
    let catId = this.props.category.categoryId;
    let catCode = this.props.category.categoryCode;
    let catDesc = this.props.category.categoryDesc;
    let hasChildren = this.state.hasChildren;
    let itemCount = this.props.itemCounter;
    let rootList = this.props.isRootList;
    let isMobile = this.props.isMobile;
    let expand = this.state.expand;
    let changeCategory = this.props.changeCategory;
    let showMore = this.props.showMore;
    let children = this.props.category.children;

    return (
        <li
          className={
                    ((hasChildren)
                    ? "menu-item-has-children"
                    : "")
                    +
                    ((rootList && itemCount > 8)
                    ? " hidden"
                    : "")
          }
          style={
            (rootList && itemCount > 8 && !showMore)
            ? {"display": "none"}
            : {"--my-left-indent": this.getIndent(catlvl,10)}
          }
          >
          <a  id={catCode}
              onClick={changeCategory}
              className={"megamenu-head"}
              style={(isMobile)
                     ? {"--my-cat-indent": this.getIndent(catlvl)}
                      : {"":""}}
              href="shop-left-sidebar.html">
            {catDesc}

            {(hasChildren && isMobile)
              ? <span>
                  <i onClick={this.expandCat}
                   className={((!expand) ? "expand" : "") + " menu-expand"}>
                  </i>
                </span>
              : null
            }
          </a>
          <ReactTransitionGroup component={React.Fragment}>
            {((hasChildren && (expand || !isMobile))
              ? <CategoryMenuItemSubList
                  renderCategoryListItems={this.props.renderCategoryListItems}
                  children={children}
                  categoryLevel={catlvl}
                  changeCategory={changeCategory}
                  itemCounter={itemCount}
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
    Velocity(element, 'slideDown', { duration: 500 , "display":""}).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideUp', { duration: 500 }).then(callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    let itemCount = this.props.itemCounter;
    let changeCategory = this.props.changeCategory;
    let children = this.props.children;

    return (
      <ul ref={this.setContainer}
          className="category-mega-menu">
            {this.props.renderCategoryListItems(children, false, changeCategory, itemCount)}
      </ul>
    )
  }
}

export default withRouter(CategoryMenuContainer);
