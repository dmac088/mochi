import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as productApi from '../../data/products/api';
import Slider from "react-slick";
import BannerSliderProduct from './BannerSliderProduct';
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
const $ = window.$;


class BannerSlider extends Component {

  next = () => {
    this.slider.slickNext();
  }

  previous = () => {
    this.slider.slickPrev();
  }

  renderProducts = (category) => {
    const { setCurrentProductId } = this.props;
    return category.products.map(product => {
      return (
          <BannerSliderProduct
            locale={this.props.locale}
            key={product.productId}
            product={product}
            categoryDesc={category.categoryDesc}
            setCurrentProductId={setCurrentProductId}
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
        const { category } = this.props;
    return (
      <Slider className="banner-slider-container" ref={c => (this.slider = c)} {...settings}>
        {this.renderProducts(category)}
      </Slider>
    )
  }
}

export default BannerSlider;
