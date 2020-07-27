import React from 'react';
import product01 from "../../../assets/images/products/product01.jpg"

function Wishlist() {
    return (
        <React.Fragment>
            <div className="page-section section mb-50">
                <div className="container">
                    <div className="row">
                        <div className="col-12">
                            <form action="#">
                                <div className="cart-table table-responsive">
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th className="pro-thumbnail">Image</th>
                                                <th className="pro-title">Product</th>
                                                <th className="pro-price">Price</th>
                                                <th className="pro-quantity">Quantity</th>
                                                <th className="pro-subtotal">Total</th>
                                                <th className="pro-remove">Remove</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td className="pro-thumbnail"><a href="#"><img src={product01} className="img-fluid" alt="Product" /></a></td>
                                                <td className="pro-title"><a href="#">Cillum dolore tortor nisl fermentum</a></td>
                                                <td className="pro-price"><span>$29.00</span></td>
                                                <td className="pro-quantity"><div className="pro-qty"><input type="text" defaultValue="1" /></div></td>
                                                <td className="pro-subtotal"><span>$29.00</span></td>
                                                <td className="pro-remove"><a href="#"><i className="fa fa-trash-o"></i></a></td>
                                            </tr>
                                            <tr>
                                                <td className="pro-thumbnail"><a href="#"><img src={product01} className="img-fluid" alt="Product" /></a></td>
                                                <td className="pro-title"><a href="#">Auctor gravida pellentesque</a></td>
                                                <td className="pro-price"><span>$30.00</span></td>
                                                <td className="pro-quantity"><div className="pro-qty"><input type="text" defaultValue="2" /></div></td>
                                                <td className="pro-subtotal"><span>$60.00</span></td>
                                                <td className="pro-remove"><a href="#"><i className="fa fa-trash-o"></i></a></td>
                                            </tr>
                                            <tr>
                                                <td className="pro-thumbnail"><a href="#"><img src={product01} className="img-fluid" alt="Product" /></a></td>
                                                <td className="pro-title"><a href="#">Condimentum posuere consectetur</a></td>
                                                <td className="pro-price"><span>$25.00</span></td>
                                                <td className="pro-quantity"><div className="pro-qty"><input type="text" defaultValue="1" /></div></td>
                                                <td className="pro-subtotal"><span>$25.00</span></td>
                                                <td className="pro-remove"><a href="#"><i className="fa fa-trash-o"></i></a></td>
                                            </tr>
                                            <tr>
                                                <td className="pro-thumbnail"><a href="#"><img src={product01} className="img-fluid" alt="Product" /></a></td>
                                                <td className="pro-title"><a href="#">Habitasse dictumst elementum</a></td>
                                                <td className="pro-price"><span>$11.00</span></td>
                                                <td className="pro-quantity"><div className="pro-qty"><input type="text" defaultValue="1" /></div></td>
                                                <td className="pro-subtotal"><span>$11.00</span></td>
                                                <td className="pro-remove"><a href="#"><i className="fa fa-trash-o"></i></a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
}

export default Wishlist;