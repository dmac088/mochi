import React, { useState, useEffect }  from 'react';
import { instance as axios } from '../../../Helpers/api/axios';
import { chunkArray } from '../../../../../services/Generic';
import Slider from "react-slick";
import { SlickArrowLeft, SlickArrowRight } from '../../HeroSlider/sliderHelper';
import Column from '../Column'
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

function Category(props) {
  const { category } = props;

  //we need local state to store products 
  const [stateObject, setObjectState] = useState({
    products: null,
  });


  //first create a local function that takes the product url and uses 
  //axios to fetch the products, then later move this into productService class
  const getProducts = () => {
    if(!category) { return; }
    axios.get(category._links.products.href)
    .then((payload) => {
      setObjectState(() => ({
        products: payload.data._embedded.productResources,
      }));
    });
  }

  useEffect(() => {
    getProducts();
  }, []);

  const renderColumns = (products, category) => {
    if (!products) { return null; }
    const chunks = chunkArray(products, 3);
    return chunks.map(chunk => {
      return (
        <Column
          key={chunks.indexOf(chunk)}
          category={category}
          products={chunk}
        />
      )
    })
  }

  let slider;
  return (
    <div key={0} className="tab-slider-container">
      <Slider ref={c => (slider = c)} {...settings}>
        {renderColumns(stateObject.products, category)}
      </Slider>
    </div>
  );
}

export default Category;