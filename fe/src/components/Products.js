import React from 'react';
import Product from './Products/Product';
import CategorySidebar from './Products/Sidebars/CategorySidebar';
import BrandSidebar from './Products/Sidebars/BrandSidebar';
import PriceSidebar from './Products/Sidebars/PriceSidebar';
import TopRatedSidebar from './Products/Sidebars/TopRatedSidebar';
import TagSidebar from './Products/Sidebars/TagSidebar';
import ShopBanner from './Products/ShopBanner';
import BreadCrumb from './Products/BreadCrumb';
import ShopHeader from './Products/ShopHeader';
import Pagination from './Products/Pagination';
import NoResults from "../empty-states/NoResults";
import ReactCSSTransitionGroup from 'react-addons-transition-group';


class Products extends Component {

  render() {
				return(
            <div class="shop-page-container mb-50">
              <div class="container">
                <div class="row">
                  <div class="col-lg-3 order-2 order-lg-1">
                    <div class="sidebar-area">
                      <CategorySidebar/>
                      <BrandSidebar/>
                      <PriceSidebar/>
                      <TopRatedSidebar/>
                      <TagSidebar/>
                    </div>
                  </div>
                  <div class="col-lg-9 order-1 order-lg-2 mb-sm-35 mb-xs-35">
                    <ShopBanner/>
                    <BreadCrumb/>
                    <ShopHeader/>
                    <div class="shop-product-wrap list row no-gutters mb-35">
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
