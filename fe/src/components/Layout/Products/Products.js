import React, { useEffect, useState, useRef } from "react";
import { useSelector } from 'react-redux';
import { instance as axios } from "../../../components/Layout/Helpers/api/axios";
import { findByDesc } from '../../../services/Category';
import Product from './Product/Grid/Product';
import CategorySidebar from './Sidebars/CategorySidebar';
import ShopBanner from './ShopBanner';
import ShopHeader from './ShopHeader';
import Pagination from './Pagination';

function Products(props) {

    const [stateObject, setObjectState] = useState({
        products: [],
    });

    const categories = useSelector(state => state.categories);
    const prevCategories = usePrevious(categories);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    useEffect(() => {    
        if(categories !== prevCategories) {    
            const { categoryDesc } = props.match.params;
            const currentCategory = findByDesc(categories.list, categoryDesc);
            if(!currentCategory) { return; }
            axios.get(currentCategory._links.products.href)
            .then((response) => {
                setObjectState({
                    products: response.data._embedded.productResources,
                })
            });
        }
    }, [categories]);

    const renderProducts = (products) => {
        return products.map(p => {
            return (
                <Product 
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
                                name={"category"}/>
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