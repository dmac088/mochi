import React, { Component } from 'react';
import Product from './Product';
import CategorySidebar from './Sidebars/CategorySidebar';
import BrandSidebar from './Sidebars/BrandSidebar';
import PriceSidebar from './Sidebars/PriceSidebar';
import TopRatedSidebar from './Sidebars/TopRatedSidebar';
import TagSidebar from './Sidebars/TagSidebar';
import ShopBanner from './ShopBanner';
import BreadCrumb from './BreadCrumb';
import ShopHeader from './ShopHeader';
import Pagination from './Pagination';
import NoResults from "../../empty-states/NoResults";
import ReactCSSTransitionGroup from 'react-addons-transition-group';


class Products extends Component {
  render() {
				return(
            <div className="shop-page-container mb-50">
              <div className="container">
                <BreadCrumb
                  match={this.props.match}/>
                <div className="row">
                  <div className="col-lg-3 order-2 order-lg-1">
                    <div className="sidebar-area">
                      <CategorySidebar/>
                      <BrandSidebar/>
                      <PriceSidebar/>
                      <TopRatedSidebar/>
                      <TagSidebar/>
                    </div>
                  </div>
                  <div className="col-lg-9 order-1 order-lg-2 mb-sm-35 mb-xs-35">
                    <ShopBanner/>
                    <ShopHeader/>
                    <div className="shop-product-wrap list row no-gutters mb-35">
                      <Product/>
                    </div>
                    <Pagination/>
                  </div>
                </div>
              </div>
            </div>
					)
				}
}


export default Products;
