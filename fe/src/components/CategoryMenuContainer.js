
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import ReactDOM from 'react-dom';
import ReactTransitionGroup from 'react-addons-transition-group';
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
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


  initMenu = () => {
    //
    //   let windows = $(window);
    //   let screenSize = windows.width();
    // /*---------------------
    //   Category Menu
    //   ------------------------*/
    //
    //   /*-- letiables --*/
    //   let categoryToggleWrap = $('.category-toggle-wrap');
    //   let categoryToggle = $('.category-toggle');
    //   let categoryMenu = $('.category-menu');
    //
    // /*
    //  *  Category Menu Default Close for Mobile & Tablet Device
    //  *  And Open for Desktop Device and Above
    //  */
    //  function categoryMenuToggle() {
    //   let screenSize = windows.width();
    //   if (screenSize <= 991) {
    //     categoryMenu.slideUp();
    //   } else {
    //     categoryMenu.slideDown();
    //   }
    //  }
    //
    //  /*-- Category Menu Toggles --*/
    //  function categorySubMenuToggle() {
    //   let screenSize = windows.width();
    //   if (screenSize <= 991) {
    //     $('.category-menu .menu-item-has-children > a').prepend('<i class="expand menu-expand"></i>');
    //     $('.category-menu .menu-item-has-children ul').slideUp();
    //     //        $('.category-menu .menu-item-has-children i').on('click', function(e){
    //     //            e.preventDefault();
    //     //            $(this).toggleClass('expand');
    //     //            $(this).siblings('ul').css('transition', 'none').slideToggle();
    //     //        })
    //   } else {
    //     $('.category-menu .menu-item-has-children > a i').remove();
    //     $('.category-menu .menu-item-has-children ul').slideDown();
    //   }
    // }
    // categoryMenuToggle();
    // windows.resize(categoryMenuToggle);
    // categorySubMenuToggle();
    // windows.resize(categorySubMenuToggle);
    //
    // categoryToggle.on('click', function () {
    //   categoryMenu.slideToggle();
    // });
    //
    // /*-- Category Sub Menu --*/
    // $('.category-menu').on('click', 'li a, li a .menu-expand', function (e) {
    //   let $a = $(this).hasClass('menu-expand') ? $(this).parent() : $(this);
    //   if ($a.parent().hasClass('menu-item-has-children')) {
    //     if ($a.attr('href') === '#' || $(this).hasClass('menu-expand')) {
    //       if ($a.siblings('ul:visible').length > 0) $a.siblings('ul').slideUp();
    //       else {
    //         $(this).parents('li').siblings('li').find('ul:visible').slideUp();
    //         $a.siblings('ul').slideDown();
    //       }
    //     }
    //   }
    //   if ($(this).hasClass('menu-expand') || $a.attr('href') === '#') {
    //     e.preventDefault();
    //     return false;
    //   }
    // });
    //
    // /*-- Sidebar Category --*/
    // let categoryChildren = $('.sidebar-category li .children');
    //
    // categoryChildren.slideUp();
    // categoryChildren.parents('li').addClass('has-children');
    //
    // $('.sidebar-category').on('click', 'li.has-children > a', function (e) {
    //
    //   if ($(this).parent().hasClass('has-children')) {
    //     if ($(this).siblings('ul:visible').length > 0) $(this).siblings('ul').slideUp();
    //     else {
    //       $(this).parents('li').siblings('li').find('ul:visible').slideUp();
    //       $(this).siblings('ul').slideDown();
    //     }
    //   }
    //   if ($(this).attr('href') === '#') {
    //     e.preventDefault();
    //     return false;
    //   }
    // });
    //
    // //More category
    //
    // $(".category-menu li.hidden").hide();
    // $("#more-btn").on('click', function (e) {
    //   e.preventDefault();
    //   $(".category-menu li.hidden").toggle(500);
    //   let htmlAfter = '<span class="icon_minus_alt2"></span> Less Categories';
    //   let htmlBefore = '<span class="icon_plus_alt2"></span> More Categories';
    //
    //
    //   if($(this).html() == htmlBefore){
    //     $(this).html(htmlAfter);
    //   }else{
    //     $(this).html(htmlBefore);
    //   }
    // });
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
      <nav  className="category-menu">

        { this.state.menuVisible ? <CategoryMenu/> : null }
      
      </nav>
    </div>
  )
}
}


class CategoryMenu extends Component {

  constructor(props) {
    super(props);
  }

  componentWillEnter (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return;}
    Velocity(element, 'slideDown', { duration: 1000 }).then(callback);
  }

  componentWillLeave (callback) {
    const element = ReactDOM.findDOMNode(this.container);
    if(element === undefined) {return;}
    Velocity(element, 'slideUp', { duration: 1000 }).then(callback);
  }

  setContainer = (c) => {
    this.container = c;
  }

  render() {
    return(
      <ul ref={this.setContainer}>
        <li><a href="shop-left-sidebar.html">Vagatables</a></li>
        <li className="menu-item-has-children"><a href="shop-left-sidebar.html">Salad</a>
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
        <li className="menu-item-has-children"><a href="shop-left-sidebar.html">Fast Foods</a>
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
        <li><a href="shop-left-sidebar.html">Beans</a></li>
        <li><a href="shop-left-sidebar.html">Bread</a></li>
        <li><a href="shop-left-sidebar.html">Fish &amp; Meats</a></li>
        <li><a href="shop-left-sidebar.html">Peanuts</a></li>
        <li><a href="shop-left-sidebar.html">Birds</a></li>
        <li className="hidden"><a href="shop-left-sidebar.html">Eggs</a></li>
        <li className="hidden"><a href="shop-left-sidebar.html">Fruits</a></li>
        <li><a href="#" id="more-btn"><span className="icon_plus_alt2" /> More Categories</a></li>
      </ul>
    )
  }
}

export default CategoryMenuContainer;
