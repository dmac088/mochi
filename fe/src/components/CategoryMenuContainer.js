
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
    };
  }

  componentDidMount() {
    this.renderMenu();
    window.addEventListener('resize', this.renderMenu);
    //More category

    $(".category-menu li.hidden").hide();
    $("#more-btn").on('click', function (e) {
      e.preventDefault();
      $(".category-menu li.hidden").toggle(500);
      var htmlAfter = '<span class="icon_minus_alt2"></span> Less Categories';
      var htmlBefore = '<span class="icon_plus_alt2"></span> More Categories';


      if($(this).html() == htmlBefore){
        $(this).html(htmlAfter);
      }else{
        $(this).html(htmlBefore);
      }
    });
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
  }

  compoenentDidMount() {
    $(".category-menu li.hidden").hide();
  }

  getSize = () => {
    return  window.innerWidth
              || document.documentElement.clientWidth
              || document.body.clientWidth;
  }

  categorySubMenuToggle = () => {
      if (this.getSize() <= 991) {
        $('.category-menu .menu-item-has-children > a').prepend('<i class="expand menu-expand"></i>');
        $('.category-menu .menu-item-has-children ul').slideUp();
      } else {
        $('.category-menu .menu-item-has-children > a i').remove();
        $('.category-menu .menu-item-has-children ul').slideDown();
      }

      //More category


  		$("#more-btn").on('click', function (e) {
  			e.preventDefault();
  			$(".category-menu li.hidden").toggle(500);
  			var htmlAfter = '<span class="icon_minus_alt2"></span> Less Categories';
  			var htmlBefore = '<span class="icon_plus_alt2"></span> More Categories';


  			if($(this).html() == htmlBefore){
  				$(this).html(htmlAfter);
  			}else{
  				$(this).html(htmlBefore);
  			}
  		});

      $('.category-menu').on('click', 'li a, li a .menu-expand', function (e) {
  			var $a = $(this).hasClass('menu-expand') ? $(this).parent() : $(this);
  			if ($a.parent().hasClass('menu-item-has-children')) {
  				if ($a.attr('href') === '#' || $(this).hasClass('menu-expand')) {
  					if ($a.siblings('ul:visible').length > 0) $a.siblings('ul').slideUp();
  					else {
  						$(this).parents('li').siblings('li').find('ul:visible').slideUp();
  						$a.siblings('ul').slideDown();
  					}
  				}
  			}
  			if ($(this).hasClass('menu-expand') || $a.attr('href') === '#') {
  				e.preventDefault();
  				return false;
  			}
  		});


    }

  componentWillEnter (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return;}
    this.categorySubMenuToggle();
    Velocity(element, 'slideDown', { duration: 1000 }).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return;}
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  changeCategory = (event) => {
    //get the query parameters
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

  renderCategoryListItems = (categoryList, changeCategory) => {
    return categoryList.map(category => {
      return(
          <li className="menu-item-has-children" key={category.categoryId} id={category.categoryCode} onClick={changeCategory}>
          <a href="shop-left-sidebar.html">{category.categoryDesc}</a>
            {/* Mega Category Menu Start */}
            <ul className="category-mega-menu">
              <li className="menu-item-has-children">
                <a className="megamenu-head" href="shop-left-sidebar.html">Vegetables</a>
                <ul>
                  <li><a href="shop-left-sidebar.html">Salad</a></li>
                  <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                  <li><a href="shop-left-sidebar.html">Fruits</a></li>
                  <li><a href="shop-left-sidebar.html">Peanuts</a></li>
                </ul>
              </li>
              <li className="menu-item-has-children">
                <a className="megamenu-head" href="shop-left-sidebar.html">Fast Foods</a>
                <ul>
                  <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                  <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                  <li><a href="shop-left-sidebar.html">Fruit</a></li>
                  <li><a href="shop-left-sidebar.html">Butter</a></li>
                </ul>
              </li>
              <li className="menu-item-has-children">
                <a className="megamenu-head" href="shop-left-sidebar.html">Salad</a>
                <ul>
                  <li><a href="shop-left-sidebar.html">Vegetables</a></li>
                  <li><a href="shop-left-sidebar.html">Fast Food</a></li>
                  <li><a href="shop-left-sidebar.html">Salad</a></li>
                  <li><a href="shop-left-sidebar.html">Peanuts</a></li>
                </ul>
              </li>
            </ul>{/* Mega Category Menu End */}
          </li>
      )
    });
  }

  render() {
    //{this.renderCategoryListItems(this.props.categoryList, this.changeCategory)}
    return(
      <ul ref={this.setContainer}>
        {this.renderCategoryListItems(this.props.categoryList, this.changeCategory)}
        <li><a href="#" id="more-btn"><span className="icon_plus_alt2" /> More Categories</a></li>
      </ul>
    )
  }
}

export default CategoryMenuContainer;
