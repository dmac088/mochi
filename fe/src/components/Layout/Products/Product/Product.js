import React, { useState, useEffect, useRef } from 'react';
import { useSelector } from 'react-redux';
import { Spinner } from '../../Helpers/Animation/Spinner';
import { instance as axios } from "../../../Layout/Helpers/api/axios";
import * as bagService from "../../../../services/Bag/index";

function Product(props) {
    const { match } = props;
    const { productCode, lang, curr } = match.params;
    
    const [stateObject, setObjectState] = useState({
        product: {},
        quantity: 1,
        loading: true,
    });

    const prevLang      = usePrevious(lang);
    const prevCurr      = usePrevious(curr);

    function usePrevious(value) {
        const ref = useRef();
        useEffect(() => {
            ref.current = value;
        });
        return ref.current;
    }

    const incrementQty = (e) => {
        e.preventDefault();
        setObjectState((prevState) => ({
            ...prevState,
            quantity: prevState.quantity + 1,
        }));
    }

    const decrementQty = (e) => {
        e.preventDefault();
        setObjectState((prevState) => ({
            ...prevState,
            quantity: prevState.quantity - 1,
        }));
    }

    const discovery = useSelector(state => state.discovery);

    const retrieveProduct = (id) => {
        const url = discovery.links.getProduct.href.replace('{code}', id);
        axios.get(url)
             .then((response) => {
                 setObjectState((prevState) => ({
                     ...prevState,
                     product: (response.data) 
                              ? response.data
                              : {},
                     loading: false,
                 }));
        });
    }

    const addToBag = (e, productCode, quantity) => {
        console.log('addToBag');
        e.preventDefault();
        bagService.addToBag(productCode, quantity);
    }

    useEffect(() => {
        if(discovery.isDone) {
            if(prevLang !== lang || prevCurr !== curr || stateObject.loading) {
                retrieveProduct(productCode);
            }
        }
    }, [discovery.isDone, stateObject.loading, lang, curr]);

    const { product } = stateObject;
    const { primaryCategory } = product;
    
    return (
        (stateObject.loading) ?
            <Spinner />
        : <div className="single-product-content ">
            <div className="container">
                <div className="single-product-content-container mb-35">
                    <div className="row">
                        <div className="col-lg-6 col-md-12 col-xs-12">
                            <div className="product-image-slider d-flex flex-custom-xs-wrap flex-sm-nowrap align-items-center mb-sm-35">
                                <div className="product-small-image-list">
                                    <div
                                        className="nav small-image-slider-single-product"
                                        role="tablist">
                                        <div className="single-small-image img-full">
                                            <a
                                                data-toggle="tab"
                                                id="single-slide-tab-1"
                                                href="#single-slide1">
                                                <img
                                                    src={product._links.defaultImage.href}
                                                    className="img-fluid"
                                                    alt=""
                                                />
                                            </a>
                                        </div>
                                        <div className="single-small-image img-full">
                                            <a
                                                data-toggle="tab"
                                                id="single-slide-tab-2"
                                                href="#single-slide2">
                                                <img
                                                    src={product._links.defaultImage.href}
                                                    className="img-fluid"
                                                    alt=""
                                                />
                                            </a>
                                        </div>
                                        <div className="single-small-image img-full">
                                            <a
                                                data-toggle="tab"
                                                id="single-slide-tab-3"
                                                href="#single-slide3"
                                            >
                                                <img 
                                                    src={product._links.defaultImage.href}
                                                    className="img-fluid"
                                                    alt=""
                                                />
                                            </a>
                                        </div>
                                        <div className="single-small-image img-full">
                                            <a
                                                data-toggle="tab"
                                                id="single-slide-tab-4"
                                                href="#single-slide4"
                                            >
                                                <img
                                                    src={product._links.defaultImage.href}
                                                    className="img-fluid"
                                                    alt=""
                                                />
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div className="tab-content product-large-image-list">
                                    <div
                                        className="tab-pane fade show active"
                                        id="single-slide1"
                                        role="tabpanel"
                                        aria-labelledby="single-slide-tab-1"
                                    >
                                        <div className="single-product-img easyzoom img-full">
                                            <img
                                                src={product._links.defaultImage.href}
                                                className="img-fluid"
                                                alt=""
                                            />
                                            <a
                                                href="assets/images/big-product-image/product04.jpg"
                                                className="big-image-popup"
                                            >
                                                <i className="fa fa-search-plus" />
                                            </a>
                                        </div>
                                    </div>
                                    <div
                                        className="tab-pane fade"
                                        id="single-slide2"
                                        role="tabpanel"
                                        aria-labelledby="single-slide-tab-2"
                                    >
                                        <div className="single-product-img easyzoom img-full">
                                            <img
                                                src={product._links.defaultImage.href}
                                                className="img-fluid"
                                                alt=""
                                            />
                                            <a
                                                href="assets/images/big-product-image/product05.jpg"
                                                className="big-image-popup" >
                                                <i className="fa fa-search-plus" />
                                            </a>
                                        </div>
                                    </div>
                                    <div
                                        className="tab-pane fade"
                                        id="single-slide3"
                                        role="tabpanel"
                                        aria-labelledby="single-slide-tab-3">
                                        <div className="single-product-img easyzoom img-full">
                                            <img
                                                src={product._links.defaultImage.href}
                                                className="img-fluid"
                                                alt="" />
                                            <a
                                                href={product._links.defaultImage.href}
                                                className="big-image-popup">
                                                <i className="fa fa-search-plus" />
                                            </a>
                                        </div>
                                    </div>
                                    <div
                                        className="tab-pane fade"
                                        id="single-slide4"
                                        role="tabpanel"
                                        aria-labelledby="single-slide-tab-4"
                                    >
                                        <div className="single-product-img easyzoom img-full">
                                            <img
                                                src={product._links.defaultImage.href}
                                                className="img-fluid"
                                                alt=""
                                            />
                                            <a
                                                href={product._links.defaultImage.href}
                                                className="big-image-popup"
                                            >
                                                <i className="fa fa-search-plus" />
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 col-md-12 col-xs-12">
                            <div className="product-feature-details">
                                <h2 className="product-title mb-15">
                                    {product.data.productDesc}
                                </h2>

                                <h2 className="product-price mb-15">
                                    <span className="main-price">{product.data.productRetail}</span>
                                    <span className="discounted-price"> {product.data.productMarkdown}</span>
                                </h2>

                                <p className="product-description mb-20">
                                    {product.data.productLongDesc}
	                            </p>

                                <div className="cart-buttons mb-20">
                                    <div className="pro-qty mr-20 mb-xs-20">
                                        <input onChange={() => console.log('change quantity')} type="text" value={stateObject.quantity} />
                                        <a onClick={incrementQty} href="#" className="inc qty-btn">+</a>
                                        <a onClick={decrementQty} href="#" className="dec qty-btn">-</a>
                                    </div>
                                    <div className="add-to-cart-btn">
                                        <a onClick={(e) => addToBag(e, productCode, stateObject.quantity)} href="#">
                                            <i className="fa fa-shopping-cart" /> Add to Bag
	                      </a>
                                    </div>
                                </div>
                                <div className="single-product-category mb-20">
                                    <h3>
                                        Categories:{" "}
                                        <span>
                                            <a href="shop-left-sidebar.html">{(primaryCategory || {}).categoryDesc}</a>,{" "}
                                        </span>
                                    </h3>
                                </div>

                                <div className="social-share-buttons">
                                    <h3>share this product</h3>
                                    <ul>
                                        <li>
                                            <a className="twitter" href="#">
                                                <i className="fa fa-twitter" />
                                            </a>
                                        </li>
                                        <li>
                                            <a className="facebook" href="#">
                                                <i className="fa fa-facebook" />
                                            </a>
                                        </li>
                                        <li>
                                            <a className="google-plus" href="#">
                                                <i className="fa fa-google-plus" />
                                            </a>
                                        </li>
                                        <li>
                                            <a className="pinterest" href="#">
                                                <i className="fa fa-pinterest" />
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="single-product-tab-section mb-35">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="tab-slider-wrapper">
                                <nav>
                                    <div className="nav nav-tabs" id="nav-tab" role="tablist">
                                        <a
                                            className="nav-item nav-link active"
                                            id="description-tab"
                                            data-toggle="tab"
                                            href="#description"
                                            role="tab"
                                            aria-selected="true"
                                        >
                                            Description
	                      </a>
                                        <a
                                            className="nav-item nav-link"
                                            id="features-tab"
                                            data-toggle="tab"
                                            href="#features"
                                            role="tab"
                                            aria-selected="false">
                                            Features
	                      </a>
                        
                                    </div>
                                </nav>
                                <div className="tab-content" id="nav-tabContent">
                                    <div
                                        className="tab-pane fade show active"
                                        id="description"
                                        role="tabpanel"
                                        aria-labelledby="description-tab"
                                    >
                                        <p className="product-desc">
                                            Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                                            Itaque obcaecati tempore reiciendis neque facere! Eos,
                                            necessitatibus? Fugit iure veritatis quidem velit quaerat quos
                                            qui pariatur dolore facilis, aliquid quae voluptatibus dicta.
                                            Quae harum velit hic molestias, eius ab cum quidem voluptates
                                            modi maiores laboriosam iusto excepturi ex, recusandae aut,
                                            facere earum ad vero aperiam. Minima dolores dignissimos ab
                                            ipsam atque placeat sapiente officia debitis nobis porro at
                                            quia veritatis, quidem id repudiandae ex tempore non. Enim
                                            soluta explicabo atque adipisci itaque.
	                      </p>
                                    </div>
                                    <div
                                        className="tab-pane fade"
                                        id="features"
                                        role="tabpanel"
                                        aria-labelledby="features-tab"
                                    >
                                        <table className="table-data-sheet">
                                            <tbody>
                                                <tr className="odd">
                                                    <td>Name</td>
                                                    <td>Kaoreet lobortis sagittis laoreet</td>
                                                </tr>
                                                <tr className="even">
                                                    <td>Color</td>
                                                    <td>Yellow</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div
                                        className="tab-pane fade"
                                        id="review"
                                        role="tabpanel"
                                        aria-labelledby="review-tab"
                                    >
                                        <div className="product-ratting-wrap">
                                            <div className="pro-avg-ratting">
                                                <h4>
                                                    4.5 <span>(Overall)</span>
                                                </h4>
                                                <span>Based on 9 Comments</span>
                                            </div>
                                            <div className="ratting-list">
                                                <div className="sin-list float-left">
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <span>(5)</span>
                                                </div>
                                                <div className="sin-list float-left">
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star-o" />
                                                    <span>(3)</span>
                                                </div>
                                                <div className="sin-list float-left">
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star-o" />
                                                    <i className="fa fa-star-o" />
                                                    <span>(1)</span>
                                                </div>
                                                <div className="sin-list float-left">
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star-o" />
                                                    <i className="fa fa-star-o" />
                                                    <i className="fa fa-star-o" />
                                                    <span>(0)</span>
                                                </div>
                                                <div className="sin-list float-left">
                                                    <i className="fa fa-star" />
                                                    <i className="fa fa-star-o" />
                                                    <i className="fa fa-star-o" />
                                                    <i className="fa fa-star-o" />
                                                    <i className="fa fa-star-o" />
                                                    <span>(0)</span>
                                                </div>
                                            </div>
                                            <div className="rattings-wrapper">
                                                <div className="sin-rattings">
                                                    <div className="ratting-author">
                                                        <h3>Cristopher Lee</h3>
                                                        <div className="ratting-star">
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <span>(5)</span>
                                                        </div>
                                                    </div>
                                                    <p>
                                                        enim ipsam voluptatem quia voluptas sit aspernatur aut
                                                        odit aut fugit, sed quia res eos qui ratione voluptatem
                                                        sequi Neque porro quisquam est, qui dolorem ipsum quia
                                                        dolor sit amet, consectetur, adipisci veli
	                            </p>
                                                </div>

                                                <div className="sin-rattings">
                                                    <div className="ratting-author">
                                                        <h3>Nirob Khan</h3>
                                                        <div className="ratting-star">
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <span>(5)</span>
                                                        </div>
                                                    </div>
                                                    <p>
                                                        enim ipsam voluptatem quia voluptas sit aspernatur aut
                                                        odit aut fugit, sed quia res eos qui ratione voluptatem
                                                        sequi Neque porro quisquam est, qui dolorem ipsum quia
                                                        dolor sit amet, consectetur, adipisci veli
	                            </p>
                                                </div>

                                                <div className="sin-rattings">
                                                    <div className="ratting-author">
                                                        <h3>MD.ZENAUL ISLAM</h3>
                                                        <div className="ratting-star">
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <i className="fa fa-star" />
                                                            <span>(5)</span>
                                                        </div>
                                                    </div>
                                                    <p>
                                                        enim ipsam voluptatem quia voluptas sit aspernatur aut
                                                        odit aut fugit, sed quia res eos qui ratione voluptatem
                                                        sequi Neque porro quisquam est, qui dolorem ipsum quia
                                                        dolor sit amet, consectetur, adipisci veli
	                            </p>
                                                </div>
                                            </div>
                                            <div className="ratting-form-wrapper fix">
                                                <h3>Add your Comments</h3>
                                                <form action="#">
                                                    <div className="ratting-form row">
                                                        <div className="col-12 mb-15">
                                                            <h5>Rating:</h5>
                                                            <div className="ratting-star fix">
                                                                <i className="fa fa-star-o" />
                                                                <i className="fa fa-star-o" />
                                                                <i className="fa fa-star-o" />
                                                                <i className="fa fa-star-o" />
                                                                <i className="fa fa-star-o" />
                                                            </div>
                                                        </div>
                                                        <div className="col-md-6 col-12 mb-15">
                                                            <label htmlFor="name">Name:</label>
                                                            <input id="name" placeholder="Name" type="text" />
                                                        </div>
                                                        <div className="col-md-6 col-12 mb-15">
                                                            <label htmlFor="email">Email:</label>
                                                            <input id="email" placeholder="Email" type="text" />
                                                        </div>
                                                        <div className="col-12 mb-15">
                                                            <label htmlFor="your-review">Your Review:</label>
                                                            <textarea
                                                                name="review"
                                                                id="your-review"
                                                                placeholder="Write a review"
                                                            />
                                                        </div>
                                                        <div className="col-12">
                                                            <input defaultValue="add review" type="submit" />
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Product;
