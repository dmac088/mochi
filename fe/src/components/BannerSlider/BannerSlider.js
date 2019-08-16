import React from 'react';
import { connect } from 'react-redux';
import * as productApi from '../../data/products/api';
import Slider from "react-slick";
import { Product } from './Product';
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/uiHelper';
const $ = window.$;

  const settings = {
    arrows: true,
    autoplay: false,
    dots: false,
    infinite: true,
    slidesToShow: 4,
    prevArrow: <SlickArrowLeft />,
    nextArrow: <SlickArrowRight />,
    responsive: [{
      breakpoint: 1499,
      settings: {
        slidesToShow: 4,
      }
    },
    {
      breakpoint: 1199,
      settings: {
        slidesToShow: 4,
      }
    },
    {
      breakpoint: 991,
      settings: {
        slidesToShow: 2,
      }
    },
    {
      breakpoint: 767,
      settings: {
        slidesToShow: 2,
      }
    },
    {
      breakpoint: 575,
      settings: {
        slidesToShow: 2,
      }
    },
    {
      breakpoint: 479,
      settings: {
        slidesToShow: 1,
      }
    }
    ]
  };

  const renderProducts = (props, slider) => {

    const { category, setCurrentProductId } = props;
    if(!category) { return }
    if(!category.products) { return }
    return category.products.map(product => {
      return (
          <Product
            key={product.productUPC}
            product={product}
            {...props}
          />
        )
    });
  }

 export const BannerSlider = (props) => {
    let slider;
    return (
      <Slider className="banner-slider-container" ref={c => (slider = c)} {...settings}>
        {renderProducts(props, slider)}
      </Slider>
    )
  }
