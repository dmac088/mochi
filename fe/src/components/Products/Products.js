import React, { Component } from 'react';
import Product from './Product';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import CategorySidebar from './Sidebars/CategorySidebar';
import BrandSidebar from './Sidebars/BrandSidebar';
import PriceSidebar from './Sidebars/PriceSidebar';
import TopRatedSidebar from './Sidebars/TopRatedSidebar';
import TagSidebar from './Sidebars/TagSidebar';
import ShopBanner from './ShopBanner';
import ShopHeader from './ShopHeader';
import Pagination from './Pagination';
import * as productApi from '../../data/products/api';
import { updateParams } from '../../services/helpers/Helper';
import * as pageService from '../../services/page';
import qs from 'query-string';

class Products extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "locale":   "en-GB",
      "currency": "HKD",
      "category": "",
      "term":     "",
      "brand":    "",
      "products": [],
      "totalPages": 0,
      "totalElements": 0,
      "numberOfElements": 0,
      "isGrid": true,
      "params":  {
                  "page": "0",
                  "size": "10",
                  "sort": "nameAsc",
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
    const params = {...this.state.params};
    const { locale, currency, term, brand } = this.props.match.params;

    const type = this.props.match.params[0];

    if(type==="category") {
      this.update(locale, currency, pathname, term, brand, Object.assign(params, qs.parse(search)), isMounting, this.getProducts);
    }
    if (type==="search") {
      this.update(locale, currency, pathname, "All", term, Object.assign(params, qs.parse(search)), isMounting, pageService.findAll);
    }
  }


  update = (locale, currency, pathname, category, term, params, isMounting = 0, callback) => {
    if(!params) {return;}
    const { page, size, sort } = params;
    if(   locale === this.state.locale
      &&  currency === this.state.currency
      &&  category === this.state.category
      &&  term === this.state.term
      &&  page === this.state.params.page
      &&  size === this.state.params.size
      &&  sort === this.state.params.sort
      &&  isMounting === 0
    ) {return;}
    callback(locale, currency, category, term, page, size, sort)
    .then((responseJSON) => {
      this.setState({
        "locale":           locale,
        "currency":         currency,
        "category":         category,
        "term":             term,
        "products":         responseJSON.content,
        "totalPages":       responseJSON.totalPages,
        "totalElements":    responseJSON.totalElements,
        "numberOfElements": responseJSON.numberOfElements,
        "params":           params,
      }, () => {
        this.props.history.push({
              "pathname": pathname,
              "search": qs.stringify(params),
        });
      });
    })
    .catch(()=>{
        console.log('failed!');
    });
  }

  getProducts = (locale, currency, category, brand, page, size, sort) =>
    productApi.findByCategory(locale, currency, category, brand, page, size, sort)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    });


  toggleGrid = (e) => {
    e.preventDefault();
    this.setState({
      "isGrid": true,
    })
  }


  toggleList= (e) => {
    e.preventDefault();
    this.setState({
      "isGrid": false,
    })
  }

  filterCategories = (categoryList, categoryDesc) => {
    return categoryList.filter(function(value, index, arr){
      return value.categoryDesc === categoryDesc;
    });
  }

  renderBrandSlider = (category, changeBrand) => {
    if(!category) { return; }
    return (
      <BrandSidebar
        changeBrand={changeBrand}
        brands={category.categoryBrands}
        match={this.props.match}
      />
    )
  }


  render() {

      const { toggleQuickView, setCurrentProductId, showQVModal, currentProductId, categoryList, changeCategory, changeBrand } = this.props;
      const { products, totalPages, totalElements, numberOfElements, isGrid, term, category } = this.state;
      const { page, size } = this.state.params;
      const cat = this.filterCategories(categoryList, category)[0];

				return(
          <React.Fragment>
            <div className="shop-page-container mb-50">
              <div className="container">
                <div className="row">
                  <div className="col-lg-3 order-2 order-lg-1">
                    <div className="sidebar-area">
                      <CategorySidebar
                        changeCategory={changeCategory}
                        category={cat}
                      />
                    {this.renderBrandSlider(cat,changeBrand)}
                      <PriceSidebar/>
                      <TopRatedSidebar/>
                      <TagSidebar/>
                    </div>
                  </div>
                  <div className="col-lg-9 order-1 order-lg-2 mb-sm-35 mb-xs-35">
                    <ShopBanner/>
                    <ShopHeader
                        totalPages={totalPages}
                        totalElements={totalElements}
                        numberOfElements={numberOfElements}
                        currentPage={page}
                        size={size}
                        toggleGrid={this.toggleGrid}
                        toggleList={this.toggleList}
                        isGrid={isGrid}
                        history={this.props.history}
                      />
                    <div className=
                              {(isGrid)
                                ? "shop-product-wrap grid row no-gutters mb-35"
                                : "shop-product-wrap row no-gutters mb-35 list"}
                    >

                        {products.map(product => {
                            return (
                                        <Product key={product.productId}
                                            product={product}
                                            setCurrentProductId={setCurrentProductId}
                                            isGrid={isGrid}
                                            match={this.props.match}
                                            history={this.props.history}/>
                                   )
                        })}

                    </div>
                    <Pagination
                      totalPages={totalPages}
                      currentPage={page}
                    />
                    <QuickViewProduct
                      {...this.props}
                      productId={currentProductId}
                      isShowing={showQVModal}
                      toggleQuickView={toggleQuickView}
                    />
                  </div>
                </div>
              </div>
            </div>
          </React.Fragment>
					)
				}
}


export default Products;
