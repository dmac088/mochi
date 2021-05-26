import React, { useEffect, useState, useRef } from "react";
import queryString from 'query-string';
import { useSelector } from 'react-redux';
import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import * as bagService from "../../../services/Bag/index";
import { findByCode, findRootNode } from '../../../services/Category';
import ProductGrid from './Product/Grid/ProductGrid';
import ProductList from './Product/List/ProductList';
import CategorySidebar from './Sidebars/CategorySidebar';
import BrandSidebar from './Sidebars/BrandSidebar';
import TagSidebar from './Sidebars/TagSidebar';
import SelectionSidebar from './Sidebars/SelectionSidebar';
import PriceSidebar from './Sidebars/PriceSidebar';
import ShopBanner from './ShopBanner';
import ShopHeader from './ShopHeader';
import Pagination from './Pagination';
import { Spinner } from '../../Layout/Helpers/Animation/Spinner';


function Products(props) {
    const { toggleQuickView, match } = props;

    const discovery = useSelector(state => state.discovery);

    const query = queryString.parse(props.location.search);

    const [stateObject, setObjectState] = useState({
        gridLayout: true,
        products: [],
        page: {},
        selectedFacets: [],
        facets: [],
        loading: false,
    });

    const addToBag = (e) => {
        e.preventDefault();
        bagService.addToBag(e.target.id);
    }

    const { categoryCode, type } = match.params;
    const categories = useSelector(state => state.categories);
    const categoriesLoading = categories.loading;


    //capture previous states
    const prevCategoryCode = usePrevious(categoryCode);
    const prevCategoriesLoading = usePrevious(categoriesLoading);
    const prevPage = usePrevious(query.page);
    const prevSize = usePrevious(query.size);
    const prevSort = usePrevious(query.sort);
    const prevQuery = usePrevious(query.q || '');


    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    const addFacet = (facet) => {
        setObjectState((prevState) => ({
            ...prevState,
            selectedFacets: [...stateObject.selectedFacets, facet],
            loading: true,
        }));
    }

    const replaceFacet = (facet) => {
        const na = [...stateObject.selectedFacets.filter(f => f.data.facetingName !== facet.data.facetingName), facet];
        setObjectState((prevState) => ({
            ...prevState,
            selectedFacets: na,
            loading: true,
        }));
    }

    const removeFacet = (facet) => {
        setObjectState((prevState) => ({
            ...prevState,
            selectedFacets: stateObject.selectedFacets.filter(f => f.data.id !== facet.data.id),
            loading: true,
        }));
    }

    useEffect(() => {
        if(categoryCode !== prevCategoryCode) {
            console.log("category changed");
            setObjectState((prevState) => ({
                ...prevState,
                selectedFacets: [],
                loading: true,
            }));
        }
    }, [categoryCode]);

    useEffect(() => {
        let isSubscribed = true;

        const currentCategory = findByCode(categories.list, categoryCode);
        const rootNode = findRootNode(categories.list);

        if (currentCategory && (
            categoryCode !== prevCategoryCode ||
            categoriesLoading !== prevCategoriesLoading ||
            stateObject.loading     ||
            query.page !== prevPage ||
            query.size !== prevSize ||
            query.sort !== prevSort ||
            query.q    !== prevQuery)) {
            
            axios.post(
                (type === 'browse') 
                ? currentCategory._links.products.href
                : discovery.links.searchProduct.href.replace('{category}', rootNode.data.id).replace('{q}', query.q),
                stateObject.selectedFacets.map(f => f.data))
                .then((response) => {
                    if (isSubscribed) {
                        setObjectState((prevState) => ({
                            ...prevState,
                            page: response.data.searchResults.page,
                            products: (response.data.searchResults._embedded) 
                                        ? response.data.searchResults._embedded.products
                                        : [],
                            facets: response.data.searchFacets || [],
                            loading: false,
                        }));
                    }
                });
        }
        return () => (isSubscribed = false);
    }, [categoryCode, categoriesLoading, stateObject.loading, query.page, query.size, query.sort, query.q]);

    const renderProducts = (products) => {
        return products.map((p, index) => {
            return (stateObject.gridLayout)
                ? <ProductGrid
                    {...props}
                    addToBag={addToBag}
                    key={index}
                    product={p}
                    toggleQuickView={toggleQuickView} />
                : <ProductList
                    {...props}
                    addToBag={addToBag}
                    key={index}
                    product={p}
                    toggleQuickView={toggleQuickView} />
        })
    }

    const changeLayout = (e) => {
        e.preventDefault();
        if (!e.target) { return; }
        const id = e.target.id;
        setObjectState((prevState) => ({
            ...prevState,
            gridLayout: id === 'grid',
        }));
    }

    return (
        <div className="shop-page-container mb-50">
            <div className="container">
                <div className="row">
                    <div className="col-lg-3 order-2 order-lg-1">
                        <div className="sidebar-area">
                            <SelectionSidebar
                                {...props}
                                type={type}
                                selectedFacets={stateObject.selectedFacets}
                                removeFacet={removeFacet} />
                            <CategorySidebar
                                {...props}
                                type={type}
                                name={"category"}
                                facets={stateObject.facets.filter(f => f.data.facetingName === 'category')}
                                selectedFacets={stateObject.selectedFacets}
                                addFacet={addFacet}
                                loading={stateObject.loading} />
                             <BrandSidebar
                                {...props}
                                type={type}
                                name={"brand"}
                                facets={stateObject.facets.filter(f => f.data.facetingName === 'brand')}
                                selectedFacets={stateObject.selectedFacets}
                                addFacet={addFacet}
                                loading={stateObject.loading} />
                            <PriceSidebar
                                {...props}
                                type={type}
                                facets={stateObject.facets.filter(f => f.data.facetingName === 'price')}
                                selectedFacets={stateObject.selectedFacets}
                                addFacet={(type === 'browse') ? replaceFacet : addFacet} 
                                loading={stateObject.loading}/>
                            <TagSidebar
                                {...props}
                                type={type}
                                facets={stateObject.facets}
                                name={"tag"}
                                facets={stateObject.facets.filter(f => f.data.facetingName === 'tag')}
                                selectedFacets={stateObject.selectedFacets}
                                addFacet={addFacet}
                                loading={stateObject.loading}
                            /> 
                        </div>
                    </div>
                    <div className="col-lg-9 order-1 order-lg-2 mb-sm-35 mb-xs-35">
                        {(stateObject.loading || categories.loading)
                            ? <Spinner />
                            : <React.Fragment>
                                <ShopBanner />
                                <ShopHeader
                                    {...props}
                                    page={stateObject.page}
                                    changeGrid={changeLayout}
                                    type={type} />
                                <div className=
                                    {(stateObject.gridLayout)
                                        ? "shop-product-wrap grid row no-gutters mb-35"
                                        : "shop-product-wrap row no-gutters mb-35 list"}>
                                    {renderProducts(stateObject.products)}
                                </div>
                                <Pagination
                                    {...props}
                                    page={stateObject.page} />
                            </React.Fragment>
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Products;