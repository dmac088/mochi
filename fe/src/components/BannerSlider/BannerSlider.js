import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as productApi from '../../data/products/api';
import Slider from "react-slick";
import { Product } from './Product';
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
const $ = window.$;


class BannerSlider extends Component {

  next = () => {
    this.slider.slickNext();
  }

  previous = () => {
    this.slider.slickPrev();
  }

  renderProducts = () => {
    const { category, setCurrentProductId } = this.props;
    return category.products.map(product => {
      return (
          <Product
            key={product.productId}
            product={product}
            {...this.props}
          />
        )
    });
  }

  render() {
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
    return (
      <Slider className="banner-slider-container" ref={c => (this.slider = c)} {...settings}>
        {this.renderProducts()}
      </Slider>
    )
  }
}

export default BannerSlider;
