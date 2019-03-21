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
import { updateParams } from '../../services/helpers/Helper';
import qs from 'query-string';

class Products extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "locale":   "en-GB",
      "term":     "",
      "products": [],
      "queryParams":  {
                        "page": 0,
                        "size": 10,
                        "sort": "productRrp",
                      }
    };
  }

  componentDidMount() {
    this.refresh(1);
  }

  componentDidUpdate() {
    this.refresh(0);
  }

  refresh = (isMounting) => {
    const { pathname, search } = this.props.location;
    const { queryParams } = this.state;
    const { locale, currency, term } = this.props.match.params;
    this.updateData(locale, pathname, term, Object.assign(queryParams,qs.parse(search)), isMounting);
  }

  updateData = (locale = "en-GB", pathname, term="All", params, isMounting = 0) => {
    if(!params) {return;}
    const { page, size, sort } = params;
    if(   locale === this.state.locale
      &&  term === this.state.term
      &&  page === this.state.queryParams.page
      &&  size === this.state.queryParams.size
      &&  sort === this.state.queryParams.sort
      &&  isMounting === 0
    ) {return;}
    this.getProducts(locale, term, page, size, sort)
    .then((responseJSON) => {
      this.setState({
        "locale":       locale,
        "term":         term,
        "products":     responseJSON.content,
        "queryParams":  params,
      }, () => {
        this.props.history.push({
              "pathname": pathname,
              "search": qs.stringify(params),
        });
      });
    });
  }

  getProducts= (locale = "en-GB", categoryDesc = "All", page, size, sort) =>
    productApi.findByCategory(locale, categoryDesc, page, size, sort)
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
      const { products } = this.state;
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
                      {products.map(product => {
                          return <Product key={product.productId}
                                          product={product}/>
                      })}
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
