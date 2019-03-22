import React, { Component } from 'react';
import { connect } from 'react-redux';
import * as productApi from '../../data/products/api';
import Slider from "react-slick";
import BannerSliderProduct from './BannerSliderProduct';
import { SlickArrowLeft, SlickArrowRight } from '../../services/helpers/Helper';
const $ = window.$;


class BannerSlider extends Component {

  constructor(props) {
    super(props);
    const { locale } = this.props.locale;
    this.state = {
                   locale: locale,
                   products: [],
                 };
  }

  componentDidMount() {
    this.updateData(this.state.locale,1);
  }

  componentDidUpdate() {
    const { locale } = this.props.locale;
    this.updateData(locale,0);
  }

  updateData = (locale = "en-GB", isMounting = 0) => {
    if(locale === this.state.locale && isMounting === 0) {return;}
    this.getProducts(locale, this.props.category.categoryId)
    .then((responseJSON) => {
      this.setState({
        products: responseJSON,
        locale: locale,
      });
    });
  }

  getProducts = (locale = "en-GB", categoryId) =>
    productApi.findPreviewByCategory(locale, categoryId)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .catch(()=>{
        console.log('getProducts failed!');
    });

  next = () => {
    this.slider.slickNext();
  }

  previous = () => {
    this.slider.slickPrev();
  }

  renderProducts = (category, products) => {
    const { setCurrentProductId } = this.props;
    return products.map(product => {
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
    const { products } = this.state;
    const { category } = this.props;
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
        {this.renderProducts(category, products)}
      </Slider>
    )
  }
}

export default BannerSlider;
