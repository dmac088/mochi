import React from 'react';
import Product from './Product/Grid/Product';
import { Sidebar } from './Sidebars/Sidebar';

function Products() {

    return (
        <div className="shop-page-container mb-50">
            <div className="container">
                <div className="row">
                    <div className="col-lg-3 order-2 order-lg-1">
                        <div className="sidebar-area">
                            <Sidebar 
                                name={"category"}/>
                            <Sidebar 
                                name={"brand"}/>
                            <Sidebar
                                name={"tag"} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Products;