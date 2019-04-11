import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
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
      "products": [],
      "totalPages": 0,
      "totalElements": 0,
      "numberOfElements": 0,
      "isGrid": true,
      "params":  {
                  "page": "0",
                  "size": "10",
                  "sort": "nameAsc",
                },
      "maxPrice": null,
      "selectedPrice": null,
    };
  }


  componentDidMount() {
    this.refresh(1);
  }


  componentDidUpdate(prevProps, prevState) {
    this.refresh(0, prevState);
  }


  refresh = (isMounting, prevState) => {
    const { pathname, search }              = this.props.location;
    const { categoryList }                  = this.props;
    const params                            = {...this.state.params};
    const { locale, currency, term, brand } = this.props.match.params;
    const type                              = this.props.match.params[0];

    if(type==="category") {
      const { selectedPrice } = this.state;
      const maxPrice = Number(this.getMaxPrice((this.filterCategories(categoryList, term)[0]), brand));

      const prevSelectedPrice = (prevState) ? prevState.selectedPrice : null;
      const prevMaxPrice = (prevState) ? prevState.maxPrice : null;

      const price = (selectedPrice) ? selectedPrice : maxPrice;
      const prevPrice = (prevSelectedPrice) ? (prevSelectedPrice) : ((prevMaxPrice) ? prevMaxPrice : 0) ;

      this.update(locale, currency, pathname, term, brand, Object.assign(params, qs.parse(search)), price, prevPrice, maxPrice, isMounting, this.getProducts);
    }
    if (type==="search") {
      this.update(locale, currency, pathname, "All", term, Object.assign(params, qs.parse(search)), price, prevPrice, maxPrice, isMounting, pageService.findAll);
    }
  }


  update = (locale, currency, pathname, category, term, params, price, prevPrice, maxPrice, isMounting = 0, callback) => {
    if(!params) {return;}
    const { page, size, sort } = params;
    const resetPrice = (!(  locale === this.state.locale
                        &&  currency === this.state.currency
                        &&  category === this.state.category
                        &&  term === this.state.term));
    if(   locale === this.state.locale
      &&  currency === this.state.currency
      &&  category === this.state.category
      &&  term === this.state.term
      &&  page === this.state.params.page
      &&  size === this.state.params.size
      &&  sort === this.state.params.sort
      &&  price === prevPrice
      &&  isMounting === 0
    ) {return;}
    callback(locale, currency, category, term, price+1, page, size, sort)
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
        "maxPrice":         maxPrice,
        "selectedPrice":    ((resetPrice) ? maxPrice : price),
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

  getProducts = (locale, currency, category, brand, maxPrice, page, size, sort) =>
    productApi.findByCategory(locale, currency, category, brand, maxPrice, page, size, sort)
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

  getMaxPrice = (category, currentBrand) => {
    console.log("getMaxPrice");
    if(!category) { return }
    let maxPrice = category.maxMarkDownPrice;
    if(!category.categoryBrands) {return maxPrice}
    if(!currentBrand) {return maxPrice}
    category.categoryBrands.map(brand => {
      if (currentBrand === brand.brandDesc) {
          maxPrice = brand.maxMarkDownPrice;
      }
    });
        console.log(maxPrice);
    return maxPrice;
  }

  renderBrandSlider = (category, changeBrand) => {
    if(!category) { return; }
    return (
      <BrandSidebar
        changeBrand={changeBrand}
        getMaxPrice={this.getMaxPrice}
        category={category}
      />
    )
  }

  renderProducts = (products, setCurrentProductId, isGrid) => {
    if(!products) {return}
    return products.map(product => {
        return (
                    <Product key={product.productId}
                        product={product}
                        setCurrentProductId={setCurrentProductId}
                        isGrid={isGrid}
                    />
               )
    })
  }

  updateMaxPrice = (value) => {
    this.setState({
      "selectedPrice": value,
    })
  }


  render() {
      const { toggleQuickView, setCurrentProductId, showQVModal, currentProductId, categoryList, changeCategory, changeBrand} = this.props;
      const { products, totalPages, totalElements, numberOfElements, isGrid, term, category, maxPrice, selectedPrice } = this.state;
      const { page, size } = this.state.params;
    //  if(!products) { return null }
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
                        getMaxPrice={this.getMaxPrice}
                        category={cat}
                      />
                    {this.renderBrandSlider(cat,changeBrand)}
                      <PriceSidebar
                        updateMaxPrice={this.updateMaxPrice}
                        maxPrice={maxPrice}
                        selectedPrice={selectedPrice}/>
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
                      />
                    <div className=
                              {(isGrid)
                                ? "shop-product-wrap grid row no-gutters mb-35"
                                : "shop-product-wrap row no-gutters mb-35 list"}
                    >
                      {this.renderProducts(products, setCurrentProductId, isGrid)}
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


export default withRouter(Products);
