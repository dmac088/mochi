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
import * as facetApi from '../../data/facets/api';
import { updateParams } from '../../services/helpers/functionHelper';
import {
  PRIMARY_CATEGORY_FACET_NAME,
  SECONDARY_CATEGORY_FACET_NAME,
  BRAND_FACET_NAME,
  PRICE_FACET_NAME,
  TAG_FACET_NAME
} from '../../services/helpers/facetHelper';
import * as pageService from '../../services/page';
import qs from 'query-string';
import _ from 'lodash';

class Products extends Component {

  constructor(props) {
    super(props);
    this.state = {
      "locale":   "en-GB",
      "currency": "HKD",
      "type": "",
      "category": "",
      "term":     "",
      "products": [],
      "facets": [],
      "syncFacets":     { "categories": [],
                          "brands": [],
                          "tags": [],
                          "prices": []},
      "selectedFacets": { "categories": [],
                          "brands": [],
                          "tags": [],
                          "prices": []},
      "totalPages": 0,
      "totalElements": 0,
      "numberOfElements": 0,
      "isGrid": true,
      "params":  {"page": "0",
                  "size": "10",
                  "sort": "nameAsc"},
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

    const price = this.state.selectedPrice;
    const facet = this.findFacet(categoryList, term);
    if(!facet) { return null; }
    const maxPrice = Number(facet.facetMaxMarkdownPrice);

    if(type==="category") {
      //get the currenct selected price
      const { selectedPrice } = this.state;
      //the incoming props are different to the local state
      const isDifferent = (!(term   === this.state.category
                           && brand === this.state.term));

      const price = (isDifferent) ? maxPrice : selectedPrice;
      this.update(type, locale, currency, pathname, term, brand, Object.assign(params, qs.parse(search)), price, 0, isMounting, selectedFacets, this.getProducts);
    }

    if (type === "search") {
      this.update(type, locale, currency, pathname, "ALL", term, Object.assign(params, qs.parse(search)), price, maxPrice, isMounting, selectedFacets, pageService.findAll);
    }
  }


  update = (type, locale, currency, pathname, category, term, params, price, maxPrice, isMounting = 0, selectedFacets, callback) => {
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

      const facets = { responseJSON };
      const newState = {
        "locale":                 locale,
        "currency":               currency,
        "type":                   type,
        "category":               category,
        "term":                   term,
        "products":               responseJSON.products.content,
        "facets":                 facets,
        "syncFacets":             selectedFacets,
        "selectedFacets":         (term !== this.state.term) ? {  "categories": [],
                                                                  "brands": [],
                                                                  "tags": [],
                                                                  "prices": []} : selectedFacets,
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
       return facetApi.findAllChildrenByCriteria(newState.locale, newState.currency, newState.category, newState.selectedFacets)
       .then((response) => {
         return response.json();
       })
       .then((response) => {
         if(type === 'category') {
            newState["facets"] = response.result;
         }
         return newState;
       })
       .catch((e) => {
         console.log(e);
       });
       return newState;
     })
     .then((newState) => {
       return  productApi.getMaxPrice(newState.locale, newState.currency, newState.category, newState.selectedFacets)
       .then((response) => {
         return response.json();
       })
       .then((response) => {
          if(type === 'category') {
            newState["maxPrice"] = response;
            if(!noChangePrice) {newState["selectedPrice"] = response;}
          }
          return newState;
       })
       .catch((e) => {
         console.log(e);
       });
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
        return response.json();
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

  findFacet = (facetList, facetDesc) => {
    return facetList.find(function(value, index, arr){
      return value.facetDisplayValue === facetDesc;
    });
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

  updateFacets = (e) => {
    const { categories, brands, tags }        = this.state.selectedFacets;
    const newSelectedFacets                   = _.cloneDeep(this.state.selectedFacets, true);

    const allFacets                           = [...categories, ...brands, ...tags];
    const removeFacet                         = allFacets.find(o => o.token  === e.currentTarget.id);

    if(removeFacet) {
        newSelectedFacets.categories          = categories.filter(o => !(removeFacet.token  === o.token));
        newSelectedFacets.brands              = brands.filter(o => !(removeFacet.token      === o.token));
        newSelectedFacets.tags                = tags.filter(o => !(removeFacet.token        === o.token));

        this.setState({
          "selectedFacets": newSelectedFacets,
        });

        return;
    }
    this.addFacet(e, newSelectedFacets);
  }

  addFacet = (e, newSelectedFacets) => {
    const { categories, brands, tags }        = this.state.facets;
    const allFacets                           = [...categories, ...brands, ...tags];
    const selectedFacet                       = allFacets.find(o => o.token  === e.currentTarget.id);

    newSelectedFacets.categories              = (selectedCategory)  ? [...newSelectedFacets.categories, selectedCategory] : [];
    newSelectedFacets.brands                  = (selectedBrand)     ? [...newSelectedFacets.brands, selectedBrand] : [];
    newSelectedFacets.tags                    = (selectedTag)       ? [...newSelectedFacets.tags, selectedTag] : [];

    this.setState({
      "selectedFacets": newSelectedFacets,
    });
  }

  getParents(facet, facets, parents) {
    if(!facet) { return null }
    if(facet.facetParentId === null) { return parents }
    const pa = facets.find(o => o.id === facet.facetParentId);
    parents.push(pa)
    return this.getParents(pa, facets, parents);
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
    if(!facet) { return; }
    const parents = this.getParents(facet, facets, []);
    return (selectedFacets.find(o => o.token === facet.token));
  }

  filterFacetsByName = (facets, name) => {
    if (!facets) { return; }
    return facets.filter(o => o.facetDisplayValue === name);
  }

  filterFacetsUnselected = (facets, selectedFacets) => {
    if (!facets) { return; }
    return facets.filter(facet => !(selectedFacets.find(o => o.token === facet.token)));
  }

  render() {
      const { toggleQuickView, setCurrentProductId, showQVModal, currentProductId, categoryList, changeCategory, changeBrand} = this.props;
      const { products, facets, selectedFacets, totalPages, totalElements, numberOfElements, isGrid, term, category, maxPrice, selectedPrice, type } = this.state;
      const { page, size } = this.state.params;
      if(!products) { return null }
      const cat = this.findFacet(categoryList, category);
				return(
          <React.Fragment>
            <div className="shop-page-container mb-50">
              <div className="container">
                <div className="row">
                  <div className="col-lg-3 order-2 order-lg-1">
                    <div className="sidebar-area">
                      <SelectionSidebar
                        selectedFacets={selectedFacets}
                        updateFacets={this.updateFacets}
                      />
                      <CategorySidebar
                        selectedFacets={selectedFacets.categories}
                        facets={this.filterFacetsUnselected(facets.categories, selectedFacets.categories)}
                        isActive={this.isActive}
                        updateFacets={this.updateFacets}
                      />
                      <BrandSidebar
                        selectedFacets={selectedFacets.brands}
                        isActive={this.isActive}
                        facets={this.filterFacetsUnselected(facets.brands, selectedFacets.brands)}
                        updateFacets={this.updateFacets}
                      />
                      <PriceSidebar
                        type={type}
                        facets={this.filterFacetsUnselected(facets.prices, selectedFacets.prices)}
                        updateSelectedPrice={this.updateSelectedPrice}
                        updateFacets={this.updateFacets}
                        isActive={this.isActive}
                        selectedFacets={selectedFacets}
                        maxPrice={maxPrice}
                        selectedPrice={selectedPrice}/>
                      <TagSidebar
                        selectedFacets={selectedFacets.tags}
                        facets={this.filterFacetsUnselected(facets.tags, selectedFacets.tags)}
                        updateFacets={this.updateFacets}
                      />
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
