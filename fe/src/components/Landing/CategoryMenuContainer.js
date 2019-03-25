import React, { Component } from 'react';
import ReactTransitionGroup from 'react-addons-transition-group';
import * as categoryApi from '../../data/categories/api';
import { isMobile, slide, updateParams } from '../../services/helpers/Helper';
import { getValue } from '../../config/lang/selector';
import 'velocity-animate/velocity.ui';


class CategoryMenuContainer extends Component {

  constructor(props) {
    super(props);
    const { locale } = this.props.match.params;
    this.state = {
      locale: locale,
      menuVisible: true,
      isMobile: false,
      categories: null
    };
  }

  filterCategories = (categoryList) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryLevel === 1;
    });
  }

  componentDidMount() {
    this.renderMenu(true);
    window.addEventListener('resize', this.renderMenu , { passive: true });
  }

  renderMenu = (isMounting = false) => {
    if(isMobile() && !this.state.isMobile) { this.setState({isMobile: true, menuVisible: (!isMounting)}) }
    if(!isMobile() && this.state.isMobile) { this.setState({isMobile: false, menuVisible: true}) }
  }

  toggleVisible = () => {
    this.setState(prevState => ({
      menuVisible: !prevState.menuVisible
    }));
  }

  render() {
    const { locale } = this.props.match.params;
    const categoryList = this.filterCategories(this.props.categoryList);
    return (
      <div className="hero-side-category">
        <div className="category-toggle-wrap">
          <button onClick={this.toggleVisible} className="category-toggle">
           <span className="arrow_carrot-right_alt2 mr-2" />
           {getValue(locale).categoryMenuHeading}
          </button>
        </div>
        <ReactTransitionGroup component="nav" className="category-menu">
          {
          ((this.state.menuVisible)
          ? <CategoryMenu
              categoryList={categoryList}
              isMobile={this.state.isMobile}
              history={this.props.history}
              match={this.props.match}
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
    slide(this.container, 'slideDown', null, callback);
  }

  componentWillLeave (callback) {
    slide(this.container, 'slideUp', null, callback);
  }

  changeCategory = (e) => {
    if(e.target.tagName === "I") {return}
    e.preventDefault();
    const { url } = this.props.match;
    const { locale, currency } = this.props.match.params;
    this.props.history.push('/'+ locale + '/'+ currency + '/category/' + e.currentTarget.text);
  }

  setContainer = (c) => {
    this.container = c;
  }

  showMore = (e) => {
    e.preventDefault();
    this.setState({
      showMore: true,
    })
  }

  showLess = (e) => {
    e.preventDefault();
    this.setState({
      showMore: false,
    })
  }

  renderCategoryListItems = (categoryList, isRootList, changeCategory, itemCounter) => {
    return categoryList.map(category => {
        if(isRootList) {itemCounter+=1};
        const { isMobile }  = this.props;
        const { showMore }  = this.state
        const categoryId    = category.categoryId;

        return(
              <ReactTransitionGroup
                    key={categoryId}
                    component={CategoryMenuItem}
                    category={category}
                    renderCategoryListItems={this.renderCategoryListItems}
                    isRootList={isRootList}
                    changeCategory={changeCategory}
                    itemCounter={itemCounter}
                    isMobile={isMobile}
                    showMore={showMore}>
              </ReactTransitionGroup>
        )
    });
  }

  render() {
    const { locale }        = this.props.match.params;
    const { categoryList }  = this.props;
    const { showMore }      = this.state;

    return(
      <ul ref={this.setContainer}>
        {this.renderCategoryListItems(categoryList, true, this.changeCategory, 0)}
        {
          ((categoryList.length > 8 && !showMore)
          ? <li>
              <a onClick={this.showMore} href="#" id="more-btn">
                <span className="icon_plus_alt2" /> {getValue(locale).moreCategories}
              </a>
            </li>
          : <li>
              <a onClick={this.showLess} href="#" id="less-btn">
                <span className="icon_minus_alt2" /> {getValue(locale).lessCategories}
              </a>
            </li>)
        }
      </ul>
    )
  }
}


class CategoryMenuItem extends Component {

  constructor(props) {
    super(props);
    const { childCategoryCount } = this.props.category;
    const { isMobile } = this.props;
    this.state = {
      hasChildren: childCategoryCount > 0,
      expand: ((isMobile) ? false : true),
    }
  }

  expandCat = (e) => {
      e.preventDefault()
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
    const { categoryLevel, categoryId, categoryCode, categoryDesc, children } = this.props.category;
    const { itemCounter, isRootList, isMobile, changeCategory, showMore } = this.props;
    const { hasChildren, expand } = this.state;

    return (
        <li
          className={
                    ((hasChildren)
                    ? "menu-item-has-children"
                    : "")
                    +
                    ((itemCounter > 8)
                    ? " hidden"
                    : "")
          }
          style={
            (isRootList && itemCounter > 8 && !showMore)
            ? {"display": "none"}
            : {"--my-left-indent": this.getIndent(categoryLevel,10)}
          }
          >
          <a  id={categoryCode}
              onClick={changeCategory}
              className={"megamenu-head"}
              style={(isMobile)
                     ? {"--my-cat-indent": this.getIndent(categoryLevel)}
                      : {"":""}}
              href="shop-left-sidebar.html">
            {categoryDesc}

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
                  categoryLevel={categoryLevel}
                  changeCategory={changeCategory}
                  itemCounter={itemCounter}
                />
              : null)}
          </ReactTransitionGroup>
        </li>
    )
  }
}


class CategoryMenuItemSubList extends Component {

  componentWillEnter (callback) {
    slide(this.container, 'slideDown', { duration: 500 , "display":""}, callback);
  }

  componentWillLeave (callback) {
    slide(this.container, 'slideUp', null, callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    const { itemCounter, changeCategory, children } = this.props;

    return (
      <ul ref={this.setContainer}
          className="category-mega-menu">
            {this.props.renderCategoryListItems(children, false, changeCategory, itemCounter)}
      </ul>
    )
  }
}

export default CategoryMenuContainer;
