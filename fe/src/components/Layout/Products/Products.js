import React, { useEffect, useState, useRef } from "react";
import queryString from 'query-string';
import { useSelector } from 'react-redux';
import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import { findByCode } from '../../../services/Category';
import { newFacet } from '../../../services/Product';
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
    const { toggleQuickView } = props;

    const query = queryString.parse(props.location.search);

    const [stateObject, setObjectState] = useState({
        gridLayout: true,
        products: [],
        page: {},
        selectedFacets: [],
        loading: false,
    });

    const { categoryCode } = props.match.params;
    const categories = useSelector(state => state.categories);
    const categoriesLoading = categories.loading;

    //capture previous states
    const prevCategoryCode = usePrevious(categoryCode);
    const prevCategoriesLoading = usePrevious(categoriesLoading);
    const prevPage = usePrevious(query.page);
    const prevSize = usePrevious(query.size);
    const prevSort = usePrevious(query.sort);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    const addFacet = (facetId, facetName, display) => {
        const na = [...stateObject.selectedFacets];
        na.push(newFacet(facetId, facetName, display));
        setObjectState((prevState) => ({
            ...prevState,
            selectedFacets: na,
            loading: true,
        }));
    }

    const replaceFacet = (facetId, facetName, display) => {
        const na = stateObject.selectedFacets.filter(f => f.facetingName !== facetName);
        na.push(newFacet(facetId, facetName, display));
        setObjectState((prevState) => ({
            ...prevState,
            selectedFacets: na,
            loading: true,
        }));
    }

    const removeFacet = (facetId) => {
        setObjectState((prevState) => ({
            ...prevState,
            selectedFacets: stateObject.selectedFacets.filter(f => f.id !== facetId),
            loading: true,
        }));
    }

    //this should be moved to a service class later on 
    const retrieveProducts = (categoryCode) => {
        const currentCategory = findByCode(categories.list, categoryCode);
        if (!currentCategory) { return; }
        axios.post(currentCategory._links.products.href,
            { facets: stateObject.selectedFacets })
            .then((response) => {
                setObjectState((prevState) => ({
                    ...prevState,
                    page: response.data.page,
                    products: (response.data._embedded)
                        ? response.data._embedded.productResources
                        : [],
                    loading: false,
                }));
            });
    }

    useEffect(() => {
        if (categoryCode !== prevCategoryCode ||
            categoriesLoading !== prevCategoriesLoading ||
            stateObject.loading ||
            query.page !== prevPage ||
            query.size !== prevSize ||
            query.sort !== prevSort) {
            retrieveProducts(categoryCode);
        }
    }, [categoryCode, categoriesLoading, stateObject.loading, query.page, query.size, query.sort]);

    const renderProducts = (products) => {
        return products.map((p, index) => {
            return (stateObject.gridLayout)
                ? <ProductGrid
                    {...props}
                    key={index}
                    product={p}
                    toggleQuickView={toggleQuickView} />
                : <ProductList
                    {...props}
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
                                selectedFacets={stateObject.selectedFacets}
                                removeFacet={removeFacet} />
                            <CategorySidebar
                                {...props}
                                name={"category"}
                                selectedFacets={stateObject.selectedFacets}
                                addFacet={addFacet}
                                loading={stateObject.loading} />
                            <BrandSidebar
                                {...props}
                                name={"brand"}
                                selectedFacets={stateObject.selectedFacets}
                                addFacet={addFacet}
                                loading={stateObject.loading} />
                            <PriceSidebar
                                {...props}
                                addFacet={replaceFacet} />
                            <TagSidebar
                                {...props}
                                name={"tag"}
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
                                    changeGrid={changeLayout} />
                                <div className=
                                    {(stateObject.gridLayout)
                                        ? "shop-product-wrap grid row no-gutters mb-35"
                                        : "shop-product-wrap row no-gutters mb-35 list"}>
                                    {renderProducts(stateObject.products)}
                                </div>
                                <Pagination
                                    {...props}
                                    page={stateObject.page} />
                                {/* {(stateObject.showQVModal && selectedProduct)
                                ? <QuickViewProduct
                                    {...props}
                                    toggleQuickView={toggleQuickView}
                                    showQVModal={stateObject.showQVModal} 
                                    product={selectedProduct} />
                                : <React.Fragment />} */}
                            </React.Fragment>
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Products;