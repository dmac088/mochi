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
      this.state = {
                     products: [],
                   };
  }

  componentWillMount() {
    console.log("componentWillMount");
    this.getProducts("en-GB", this.props.category.categoryId);
  }

  componentDidMount() {
    console.log("componentDidMount");
  }

  getProducts = (lang = "en-GB", categoryId) =>
    productApi.findPreviewByCategory(lang, categoryId)
    .then((response) => {
        return response.text();
    })
    .then((responseText) => {
        return JSON.parse(responseText);
    })
    .then((responseJSON) => {
        this.setState({
          products: responseJSON,
        })
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
    return products.map(product => {
      return (
          <BannerSliderProduct
            key={product.productId}
            product={product}
            categoryDesc={category.categoryDesc}
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
