import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { Product } from './Product';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import CategorySidebar from './Sidebars/CategorySidebar';
import { BrandSidebar } from './Sidebars/BrandSidebar';
import { PriceSidebar } from './Sidebars/PriceSidebar';
import { TopRatedSidebar } from './Sidebars/TopRatedSidebar';
import { TagSidebar } from './Sidebars/TagSidebar';
import { ShopBanner } from './ShopBanner';
import { ShopHeader } from './ShopHeader';
import { Pagination } from './Pagination';
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
      "categoryFacets": [],
      "brandFacets": [],
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
      "prevPrice": null,
    };
  }


  componentDidMount() {
    this.refresh(1);
  }


  componentDidUpdate(prevProps) {
    this.refresh(0);
  }


  refresh = (isMounting, categoryFacets) => {
    console.log(categoryFacets);
    const { pathname, search }              = this.props.location;
    const { categoryList }                  = this.props;
    const params                            = {...this.state.params};
    const { locale, currency, term, brand } = this.props.match.params;
    const type                              = this.props.match.params[0];

    if(type==="category") {

      //get the max price for our new props
      const maxPrice = Number(this.getMaxPrice((this.filterCategories(categoryList, term)[0]), brand));
      if(Number.isNaN(maxPrice)) { return }

      //get the currenct selected price
      const { selectedPrice } = this.state;

      //the incoming props are different to the local state
      const isDifferent = (!(term === this.state.category
                            && brand === this.state.term));

      const price = (isDifferent) ? maxPrice : selectedPrice;
      this.update(locale, currency, pathname, term, brand, Object.assign(params, qs.parse(search)), price, maxPrice, isMounting, null, this.getProducts);
    }
    if (type === "search") {
      const price = this.state.selectedPrice;
      const maxPrice = Number(this.getMaxPrice((this.filterCategories(categoryList, term)[0]), brand));
      this.update(locale, currency, pathname, "All", term, Object.assign(params, qs.parse(search)), price, maxPrice, isMounting, categoryFacets, pageService.findAll);
    }
  }


  update = (locale, currency, pathname, category, term, params, price, maxPrice, isMounting = 0, categoryFacets, callback) => {
    if(!params) {return;}
    const { page, size, sort } = params;
    if(   locale      === this.state.locale
      &&  currency    === this.state.currency
      &&  category    === this.state.category
      &&  term        === this.state.term
      &&  page        === this.state.params.page
      &&  size        === this.state.params.size
      &&  sort        === this.state.params.sort
      &&  price       === this.state.prevPrice
      &&  isMounting  === 0
    ) {return;}
    callback(locale, currency, category, term, price+1, page, size, sort)
    .then((responseJSON) => {
      this.setState({
        "locale":           locale,
        "currency":         currency,
        "category":         category,
        "term":             term,
        "products":         responseJSON.products.content,
        "categoryFacets":   responseJSON.categoryfacets,
        "brandFacets":      responseJSON.brandFacets,
        "totalPages":       responseJSON.products.totalPages,
        "totalElements":    responseJSON.products.totalElements,
        "numberOfElements": responseJSON.products.numberOfElements,
        "params":           params,
        "maxPrice":         maxPrice,
        "selectedPrice":    price,
        "prevPrice":        price,
      });
     })
      .then(() => {
        this.props.history.push({
          "pathname": pathname,
          "search": qs.stringify(params),
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
    if(!category) { return }
    let maxPrice = category.maxMarkDownPrice;
    if(!category.categoryBrands) {return maxPrice}
    if(!currentBrand) {return maxPrice}
    category.categoryBrands.map(brand => {
      if (currentBrand === brand.brandDesc) {
          maxPrice = brand.maxMarkDownPrice;
      }
    });
    return maxPrice;
  }

  renderProducts = (category, products, setCurrentProductId, isGrid) => {
    if(!products) {return}
    return products.map(product => {
        return (
                    <Product key={product.productId}
                        category={category}
                        product={product}
                        setCurrentProductId={setCurrentProductId}
                        isGrid={isGrid}
                    />
               )
    })
  }

  updateSelectedPrice = (value) => {
    this.setState({
      "selectedPrice": value,
    })
  }

  updateMaxPrice = (category, brand) => {
    this.setState({
      "maxPrice": value,
    })
  }

  render() {
      const { toggleQuickView, setCurrentProductId, showQVModal, currentProductId, categoryList, changeCategory, changeBrand} = this.props;
      const { products, categoryFacets, brandFacets, totalPages, totalElements, numberOfElements, isGrid, term, category, maxPrice, selectedPrice } = this.state;
      const { page, size } = this.state.params;

      if(!products) { return null }
      const cat = this.filterCategories(categoryList, category)[0];
				return(
          <React.Fragment>
            <div className="shop-page-container mb-50">
              <div className="container">
                <div className="row">
                  <div className="col-lg-3 order-2 order-lg-1">
                    <div className="sidebar-area">
                      <CategorySidebar
                        refresh={this.refresh}
                        category={cat}
                        categoryFacets={categoryFacets}
                        changeCategory={changeCategory}
                      />
                      <BrandSidebar
                        category={cat}
                        brandFacets={brandFacets}
                      />
                      <PriceSidebar
                        updateSelectedPrice={this.updateSelectedPrice}
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
                      {this.renderProducts(cat, products, setCurrentProductId, isGrid)}
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
