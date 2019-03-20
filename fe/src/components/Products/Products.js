import React, { Component } from 'react';
import Header from '../Header/Header';
import Product from './Product';
import CategorySidebar from './Sidebars/CategorySidebar';
import BrandSidebar from './Sidebars/BrandSidebar';
import PriceSidebar from './Sidebars/PriceSidebar';
import TopRatedSidebar from './Sidebars/TopRatedSidebar';
import TagSidebar from './Sidebars/TagSidebar';
import ShopBanner from './ShopBanner';
import BreadCrumb from '../BreadCrumb';
import ShopHeader from './ShopHeader';
import Pagination from './Pagination';
import * as productApi from '../../data/products/api';

class Products extends Component {

  constructor(props) {
    super(props);
    const { locale } = this.props.match.params;
    this.state = {
      products: [],
    };
  }

  componentDidMount() {
    const { locale, term } = this.props.match.params;
    this.updateData(this.state.locale, term, 1);
  }

  componentDidUpdate() {
    const { locale } = this.props.match.params;
    //this.updateData(locale, 0);
  }

  updateData = (locale = "en-GB", term="All", isMounting = 0) => {
    if(locale === this.state.locale && isMounting === 0) {return;}
    this.getProducts(locale, term, 1)
    .then((responseJSON) => {
      this.setState({
        products: responseJSON,
        locale: locale,
      });
    });
  }

  getProducts= (locale = "en-GB", categoryDesc = "All") =>
    productApi.findByCategory(locale, categoryDesc)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .catch(()=>{
        console.log('getProducts failed!');
  });

  render() {
      console.log(this.state);
				return(
          <React.Fragment>
            <Header
              {...this.props}
            />
            <div className="shop-page-container mb-50">
              <div className="container">
                <BreadCrumb
                  match={this.props.match}
                  page="Shop"/>
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
                    <div className="shop-product-wrap grid row no-gutters mb-35">
                      <Product/>
                    </div>
                    <Pagination/>
                  </div>
                </div>
              </div>
            </div>
          </React.Fragment>
					)
				}
}


export default Products;
