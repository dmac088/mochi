import React, { Component } from 'react';
import ReactTransitionGroup from 'react-addons-transition-group';
import { withRouter } from 'react-router-dom';
import * as categoryApi from '../../data/categories/api';
import { isMobile, slide, updateParams } from '../../services/helpers/uiHelper';
import { changeCategory } from '../../services/helpers/routeHelper';
import { filterCategories } from '../../services/helpers/filterHelper';
import { getValue } from '../../config/lang/selector';
import { createRouteProps } from '../../services/helpers/routeHelper';
import 'velocity-animate/velocity.ui';


class CategoryMenuContainer extends Component {

  constructor(props) {
    super(props);
    this.state = {
      menuVisible: true,
      isMobile: false,
      categories: null
    };
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
    const { menuVisible, isMobile } = this.state;
    const routeProps = createRouteProps(this.props.history, this.props.match, this.props.location);
    const { locale } = routeProps.match.params;
    const { categoryList } = this.props;

    //const categoryList = filterCategories(this.props.categoryList, 'LNDMM01');
    return (
      <div className="hero-side-category">
        <div className="category-toggle-wrap">
          <button onClick={this.toggleVisible} className="category-toggle">
           <span className="arrow_carrot-right_alt2 mr-2" />
           {getValue(locale).categoryMenuHeading}
          </button>
        </div>
        <ReactTransitionGroup
          component="nav"
          className="category-menu">
            {((menuVisible)
            ? <CategoryMenu
                locale={locale}
                categoryList={categoryList}
                isMobile={isMobile}
                routeProps={routeProps}
              />
            : null)}
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

  renderCategoryList = (locale, isMobile, displayCategoryList, categoryList, isRootList, itemCounter, routeProps) => {
    if(!displayCategoryList) { return; }
    return displayCategoryList.map(category => {
        if(isRootList) {itemCounter+=1};
        const { showMore }  = this.state
        const categoryId    = category.facetId;

        return(
              <ReactTransitionGroup
                    key={categoryId}
                    component={CategoryMenuItem}
                    locale={locale}
                    isMobile={isMobile}
                    category={category}
                    isRootList={isRootList}
                    itemCounter={itemCounter}
                    routeProps={routeProps}
                    showMore={showMore}
                    displayCategoryList={displayCategoryList}
                    categoryList={categoryList}
                    renderCategoryList={this.renderCategoryList}>
              </ReactTransitionGroup>
        )
    });
  }

  render() {
    const { categoryList, isMobile, routeProps }  = this.props;
    const { locale }                              = routeProps.match.params;
    const { showMore }                            = this.state;
    const displayCategoryList                     = categoryList.filter(o => o.facetLevel === 1);

    return(
      <ul ref={this.setContainer}>
        {this.renderCategoryList(locale, isMobile, displayCategoryList, categoryList, true, 0, routeProps)}
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
    const { facetChildCount } = this.props.category;
    const { isMobile } = this.props;
    this.state = {
      hasChildren: facetChildCount > 0,
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

  getChildren = (parent, categoryList, children) => {
    const c = categoryList.filter(o => o.facetParentId === parent.facetId);
    if(!c) {return children;}
    c.map((child) => {
                      children.push(child);
                      this.getChildren(child, categoryList, children);
                      });

    return c;
  }

  render() {
    const { locale, itemCounter, isRootList, isMobile, showMore, displayCategoryList, category, routeProps, categoryList, renderCategoryList } = this.props;
    const { hasChildren, expand } = this.state;
    const children = [];
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
            : {"--my-left-indent": this.getIndent(category.facetLevel, 10)}
          }
          >
          <a  id={category.facetDisplayValue}
              onClick={(e) => {
                          if ((e.target.tagName.toLowerCase() === "i")) {return}
                          changeCategory(e, routeProps )
                        }}
              className={"megamenu-head"}
              style={(isMobile)
                     ? {"--my-cat-indent": this.getIndent(category.facetLevel)}
                      : {"":""}}
              href="shop-left-sidebar.html">
            {category.facetDisplayValue}
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
                  locale={locale}
                  isMobile={isMobile}
                  displayCategoryList={displayCategoryList}
                  categoryList={categoryList}
                  children={this.getChildren(category, categoryList, children)}
                  categoryLevel={category.facetLevel}
                  itemCounter={itemCounter}
                  routeProps={routeProps}
                  renderCategoryList={renderCategoryList}
                />
              : null)}
          </ReactTransitionGroup>
        </li>
    )
  }
}


class CategoryMenuItemSubList extends Component {

  componentWillEnter (callback) {
    if(!this.container) { return }
    slide(this.container, 'slideDown', { duration: 500 , "display":""}, callback);
  }

  componentWillLeave (callback) {
    if(!this.container) { return }
    slide(this.container, 'slideUp', null, callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    const { locale, itemCounter, children, isMobile, routeProps, categoryList, renderCategoryList } = this.props;
    return (
      <ul ref={this.setContainer}
          className="category-mega-menu">
            {renderCategoryList(locale, isMobile, children, categoryList, false, itemCounter, routeProps)}

      </ul>
    )
  }
}

export default withRouter(CategoryMenuContainer);
