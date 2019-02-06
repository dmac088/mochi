
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
import qs from 'query-string';
const $ = window.$;


class CategoryMenuContainer extends Component {


  constructor(props) {
    super(props);
    this.state = {
        menuVisible: true,
        expandId: null,
        expand: false,
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
        { this.state.menuVisible ?
          <CategoryMenu
            categoryList={this.props.categoryList}
          />
          : null }
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

  getSize = () => {
    return  window.innerWidth
              || document.documentElement.clientWidth
              || document.body.clientWidth;
  }

  componentWillEnter (callback) {
    console.log("componentWillEnter");
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideDown', { duration: 1000 }).then(callback);
  }

  componentWillLeave (callback) {
    console.log("componentWillLeave");
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return}
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  changeCategory = (event) => {
    //get the query parameters
    // let urlParams = (qs.parse(this.props.history.location.search));
    // let mergedParams = Object.assign(urlParams, {
    //                                               category: event.target.id,
    //                                               page: 0
    //                                             });
    // const searchString = qs.stringify(mergedParams);
    // this.props.history.push({
    //   "pathname": '/Search',
    //   "search": searchString,
    // });
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


  expandCat = (e) => {
    e.preventDefault();
    let id = Number(e.target.id);
    this.setState(prevState => ({
      expandId: id,
      expand: (prevState.expandId === id) ? !prevState.expand : true,
    }));
  }

  renderCategoryListItems = (categoryList, isRootList, changeCategory, itemCounter) => {
    return categoryList.map(category => {
        if(isRootList) {itemCounter+=1};
      return(

          <li className={
              ((category.childCategoryCount > 0)
              ? "menu-item-has-children"
              : "")
              +
              ((isRootList && itemCounter > 8)
              ? " hidden"
              : "")
            }
            style={
              (isRootList && itemCounter > 8 && !this.state.showMore)
              ? {"display": "none"}
              : null
            }
              key={category.categoryId}
              id={category.categoryCode}
              onClick={this.changeCategory}>
              <a className={(category.childCategoryCount > 0) ? "megamenu-head" : null} href="shop-left-sidebar.html">{category.categoryDesc}
                {(category.childCategoryCount > 0 && this.getSize() <= 991)
                  ? <i id={category.categoryId} onClick={e => this.expandCat(e)} className="expand menu-expand"></i>
                  : null
                }
              </a>
              {
              (category.childCategoryCount > 0) ?
                <ul className="category-mega-menu"

                    style={  //if the id of the clicked element matches category.categoryId
                             //then nothing, else set style = display : none
                             (((this.state.expandId === category.categoryId) && this.state.expand)
                             //show
                             ? null
                             //hide
                             :  (this.getSize() <= 991) ? {"display":"none"} : null)

                          }>
                  {this.renderCategoryListItems(category.children, false, changeCategory, itemCounter)}
                </ul>
              : null
              }
          </li>
      )
    });
  }

  render() {
    return(
      <ul ref={this.setContainer} >
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

export default CategoryMenuContainer;
