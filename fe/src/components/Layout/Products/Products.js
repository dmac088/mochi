import React, { useEffect, useState, useRef } from "react";
import { useSelector } from 'react-redux';
import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import { findByCode } from '../../../services/Category';
import Product from './Product/Grid/Product';
import CategorySidebar from './Sidebars/CategorySidebar';
import ShopBanner from './ShopBanner';
import ShopHeader from './ShopHeader';
import Pagination from './Pagination';

function Products(props) {

    const [stateObject, setObjectState] = useState({
        products: [],
        facets: [],
        loading: false,
    });

    const { categoryCode } = props.match.params;
    const categories = useSelector(state => state.categories);
    const { loading } = categories;

    const prevCategoryCode = usePrevious(categoryCode);
    const prevCategoryLoading = usePrevious(loading);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    const addFacet = (facetId, facetName) => {
        const newArray = [...stateObject.facets];
        newArray.push({
            "type": "EntityFacet",
			"facetingName": facetName,
			"id": facetId,
        })
        setObjectState((prevState) => ({
            ...prevState,
            facets: newArray,
        }));
        console.log(stateObject.facets);
    }

    //this should be moved to a service class later on 
    const retrieveProducts = (categoryCode) => {
        const currentCategory = findByCode(categories.list, categoryCode);
            if(!currentCategory) { return; }
            axios.get(currentCategory._links.products.href)
            .then((response) => {
                setObjectState((prevState) => ({
                    ...prevState,
                    products: (response.data._embedded) 
                              ? response.data._embedded.productResources
                              : [],
                    loading: false,
                }));
            });
    }

    useEffect(() => {    
        if(categoryCode !== prevCategoryCode || loading !== prevCategoryLoading) {   
            retrieveProducts(categoryCode);
        }
    }, [categoryCode, loading]);

    const renderProducts = (products) => {
        return products.map((p, index) => {
            return (
                <Product 
                    key={index}
                    product={p}/>
            )
        })
    }

    return (
        <div className="shop-page-container mb-50">
            <div className="container">
                <div className="row">
                    <div className="col-lg-3 order-2 order-lg-1">
                        <div className="sidebar-area">
                            <CategorySidebar 
                                {...props}
                                name={"category"}
                                addFacet={addFacet}/>
                        </div>
                    </div>
                    <div className="col-lg-9 order-1 order-lg-2 mb-sm-35 mb-xs-35">
                        <ShopBanner/>
                        <ShopHeader />
                        <div className="shop-product-wrap grid row no-gutters mb-35">
                            {renderProducts(stateObject.products)}
                        </div>
                        <Pagination />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Products;