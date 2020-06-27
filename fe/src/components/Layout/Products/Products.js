import React from 'react';
import Product from './Product/Grid/Product';
import CategorySidebar from './Sidebars/CategorySidebar';

function Products(props) {

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
                </div>
            </div>
        </div>
    )
}

export default Products;