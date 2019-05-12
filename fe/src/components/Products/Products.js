import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { Product } from './Product';
import QuickViewProduct from '../QuickView/QuickViewProduct';
import { SelectionSidebar } from './Sidebars/SelectionSidebar';
import { CategorySidebar } from './Sidebars/CategorySidebar';
import { BrandSidebar } from './Sidebars/BrandSidebar';
import { PriceSidebar } from './Sidebars/PriceSidebar';
import { TopRatedSidebar } from './Sidebars/TopRatedSidebar';
import { TagSidebar } from './Sidebars/TagSidebar';
import { ShopBanner } from './ShopBanner';
import { ShopHeader } from './ShopHeader';
import { Pagination } from './Pagination';
import * as categoryApi from '../../data/categories/api';
import * as productApi from '../../data/products/api';
import * as brandApi from '../../data/brands/api';
import { updateParams } from '../../services/helpers/Helper';
import * as pageService from '../../services/page';
import qs from 'query-string';
import _ from 'lodash';

class Products extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "locale":   "en-GB",
      "currency": "HKD",
      "category": "",
      "term":     "",
      "products": [],
      "facets": [],
      "syncFacets": [],
      "selectedFacets": [],
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
      "syncPrice": null,
    };
  }


  componentDidMount() {
    this.refresh(1, this.state.selectedFacets);
  }


  componentDidUpdate(prevProps) {
    this.refresh(0, this.state.selectedFacets);
  }


  refresh = (isMounting, selectedFacets) => {
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
      this.update(type, locale, currency, pathname, term, brand, Object.assign(params, qs.parse(search)), price, 0, isMounting, selectedFacets, this.getProducts);
    }

    if (type === "search") {
      const price = this.state.selectedPrice;
      const maxPrice = Number(this.getMaxPrice((this.filterCategories(categoryList, term)[0]), brand));
      this.update(type, locale, currency, pathname, "ALL", term, Object.assign(params, qs.parse(search)), price, maxPrice, isMounting, selectedFacets, pageService.findAll);
    }
  }


  update = (type, locale, currency, pathname, category, term, params, price, maxPrice, isMounting = 0, selectedFacets = [], callback) => {
    if(!params) {return;}
    const { page, size, sort } = params;
    const noChangePrice = ( currency === this.state.currency
                            &&  (_.isEqual(selectedFacets, this.state.syncFacets)));
    if(   locale      === this.state.locale
      &&  noChangePrice
      &&  category    === this.state.category
      &&  term        === this.state.term
      &&  page        === this.state.params.page
      &&  size        === this.state.params.size
      &&  sort        === this.state.params.sort
      &&  price       === this.state.syncPrice
      &&  isMounting  === 0
    ) {return;}
    callback(locale, currency, category, term, price+1, page, size, sort, selectedFacets)
    .then((responseJSON) => {
      const facets = (responseJSON.facets) ? responseJSON.facets.filter(o => o.level !== 0) : null;
      const newState = {
        "locale":                 locale,
        "currency":               currency,
        "category":               category,
        "term":                   term,
        "products":               responseJSON.products.content,
        "facets":                 facets,
        "syncFacets":             selectedFacets,
        "selectedFacets":         (term !== this.state.term) ? [] : selectedFacets,
        "totalPages":             responseJSON.products.totalPages,
        "totalElements":          responseJSON.products.totalElements,
        "numberOfElements":       responseJSON.products.numberOfElements,
        "params":                 params,
        "maxPrice":               maxPrice,
        "selectedPrice":          price,
        "syncPrice":              price,
      };
      return newState;
     })
     .then((newState) => {
       //add the category children to the facets array
       return categoryApi.findAllChildren(newState.locale, newState.currency, newState.category, newState.selectedFacets.filter(o => o.facetingName === "BrandFR"))
       .then((response) => {
         return response.text();
       })
       .then((responseText) => {
         if(type === 'category') {
            newState["facets"] = [...JSON.parse(responseText)];
         }
         return newState;
       })
       .catch((e) => {console.log(e)});
       return newState;
     })
     .then((newState) => {
       return brandApi.findByCategory(newState.locale, newState.currency, newState.category, newState.selectedFacets.filter(o => o.facetingName === "CategoryFR"))
       .then((response) => {
         return response.text();
       })
       .then((responseText) => {
         if(type === 'category') {
            newState["facets"] = [...newState["facets"], ...JSON.parse(responseText)];
         }
         return newState;
       })
       .catch((e) => {console.log(e)});
     })
     .then((newState) => {
       return  productApi.getMaxPrice(newState.locale, newState.currency, newState.category, newState.selectedFacets)
       .then((response) => {
         return response.text();
       })
       .then((responseText) => {
          if(type === 'category') {
            newState["maxPrice"] = JSON.parse(responseText);
            if(!noChangePrice) {newState["selectedPrice"] = JSON.parse(responseText);}
          }
          return newState;
       })
       .catch((e) => {console.log(e)});
     })
     .then((newState) => {
       this.setState({
         ...newState
       });
     })
     .catch((e)=>{
       console.log(e);
     });
  }

  getProducts = (locale, currency, category, brand, maxPrice, page, size, sort, facets) =>
    productApi.findByCategory(locale, currency, category, brand, maxPrice, page, size, sort, facets)
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
    });
  }

  applyFacet = (e, props) => {
    const removeFacet = this.state.selectedFacets.find(o => o.token === e.currentTarget.id);
    if(removeFacet) {
        const removalSet        = [removeFacet];
        const newSelectedFacets = this.state.selectedFacets.filter(
                                    o => !(removalSet.find(d => d.token === o.token)))

        this.setState({
          "selectedFacets": newSelectedFacets,
        });
        return;
    }

    const newSelectedFacets = _.cloneDeep(this.state.selectedFacets, true);
    const addFacet = this.state.facets.find(o => o.token === e.currentTarget.id);
    const selectedSet = [...newSelectedFacets, addFacet];

    this.setState({
      "selectedFacets": selectedSet,
    });
  }

  getParents(facet, facets, parents) {
    if(!facet) { return null }
    if(facet.parentId === null) { return parents }
    const pa = facets.filter(o => o.id === facet.parentId);
    parents.push(pa[0])
    return this.getParents(pa[0], facets, parents);
  }

  getChildren(parent, facets, children) {
    const ch = facets.filter(o => o.parentId === parent.id);
    if (ch.length === 0) { return children }
    ch.map(c => {
      children.push(c);
      return this.getChildren(c, facets, children);
    });
    return children;
  }

  isActive = (facet, selectedFacets, facets) => {
    if(!facet) { return }
    const parents = this.getParents(facet, facets, [])
    const parentIsSelected =  (parents) ? (parents.filter(parent => { return !(selectedFacets.findIndex(o => o.token === parent.token) === -1) }).length > 0) : false;
    return !(selectedFacets.findIndex(o => o.token === facet.token) === -1)
  }

  filterFacetsByName = (facets, name) => {
    if(!facets) { return }
    return facets.filter(o => o.facetingName === name);
  }

  filterFacetsUnselected = (facets, selectedFacets) => {
    if(!facets) { return }
    if(!selectedFacets) { return }
    return facets.filter(facet => (selectedFacets.findIndex(o => o.token === facet.token) === -1))
  }

  render() {

      const { toggleQuickView, setCurrentProductId, showQVModal, currentProductId, categoryList, changeCategory, changeBrand} = this.props;
      const { products, facets, selectedFacets, totalPages, totalElements, numberOfElements, isGrid, term, category, maxPrice, selectedPrice } = this.state;
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
                      <SelectionSidebar
                        selectedFacets={selectedFacets}
                        applyFacet={this.applyFacet}
                      />
                      <CategorySidebar
                        selectedFacets={selectedFacets}
                        facets={this.filterFacetsUnselected(this.filterFacetsByName(facets, "CategoryFR"), selectedFacets)}
                        isActive={this.isActive}
                        applyFacet={this.applyFacet}
                      />
                      <BrandSidebar
                        selectedFacets={selectedFacets}
                        isActive={this.isActive}
                        facets={this.filterFacetsUnselected(this.filterFacetsByName(facets, "BrandFR"), selectedFacets)}
                        applyFacet={this.applyFacet}
                      />
                      <PriceSidebar
                        facets={this.filterFacetsUnselected(this.filterFacetsByName(facets, "PriceFR"), selectedFacets)}
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
