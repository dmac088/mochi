import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import * as categoryApi from '../../data/categories/api';
import { isMobile, slide, updateParams } from '../../services/helpers/Helper';
import langSelector from '../../config/lang/selector';
import 'velocity-animate/velocity.ui';


class CategoryMenuContainer extends Component {

  constructor(props) {
    super(props);
    const { locale } = this.props.match.params;
    this.state = {
      locale: locale,
      menuVisible: true,
      isMobile: false,
      categoryList: [],
    };
  }

  componentDidMount() {
    this.updateMenu(this.state.locale,1);
    this.renderMenu(true);
    window.addEventListener('resize', this.renderMenu , { passive: true });
  }

  componentDidUpdate() {
    const { locale } = this.props.match.params;
    this.updateMenu(locale, 0);
  }

  updateMenu = (locale = "en-GB", isMounting = 0) => {
    if(locale === this.state.locale && isMounting === 0) {return;}
    this.getCategories(locale, 1)
    .then((responseJSON) => {
      this.setState({
        categoryList: responseJSON,
        locale: locale,
      });
    });
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

  getCategories = (locale = "en-GB", level = 1) =>
    categoryApi.findAllForLevel(locale, level)
    .then((response) => {
      return response.text();
    })
    .then((responseText) => {
      return JSON.parse(responseText);
    })
    .catch(()=>{
      console.log('getCategories failed!');
  });

  render() {
    const { locale } = this.props.match.params;
    return (
      <div className="hero-side-category">
        <div className="category-toggle-wrap">
          <button onClick={this.toggleVisible} className="category-toggle">
           <span className="arrow_carrot-right_alt2 mr-2" />
           {langSelector[locale].categoryMenuHeading}
          </button>
        </div>
        <ReactTransitionGroup component="nav" className="category-menu">
          {
          ((this.state.menuVisible)
          ? <CategoryMenu
              categoryList={this.state.categoryList}
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
        let categoryId = category.categoryId;
        let isMobile = this.props.isMobile;
        let showMore = this.state.showMore;
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
    const { locale } = this.props.match.params;
    let categoryList = this.props.categoryList;
    let showMore = this.state.showMore;
    return(
      <ul ref={this.setContainer}>
        {this.renderCategoryListItems(categoryList, true, this.changeCategory, 0)}
        {
          ((categoryList.length > 8 && !showMore)
          ? <li>
              <a onClick={this.showMore} href="#" id="more-btn">
                <span className="icon_plus_alt2" /> {langSelector[locale].moreCategories}
              </a>
            </li>
          : <li>
              <a onClick={this.showLess} href="#" id="less-btn">
                <span className="icon_minus_alt2" /> {langSelector[locale].lessCategories}
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
    this.state = {
      hasChildren: this.props.category.childCategoryCount > 0,
      expand: ((this.props.isMobile) ? false : true),
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
                    ((itemCount > 8)
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
    slide(this.container, 'slideDown', { duration: 500 , "display":""}, callback);
  }

  componentWillLeave (callback) {
    slide(this.container, 'slideUp', null, callback);
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
