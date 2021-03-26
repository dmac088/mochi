import React from "react";
import HeroSlider from "./HeroSlider/HeroSlider";
import Policy from "./Policy/Policy";
import Highlights from "./Highlights/Highlights";
import Banner from "./Banner/Banner";
import BestSeller from "./BestSeller/BestSeller";
import BrandSlider from "./BrandSlider/BrandSlider";

function Landing(props) { 
  
  return (
    <React.Fragment>
      <HeroSlider 
        {...props}/>
      <Policy />
      <Highlights 
        {...props}/>
      <Banner />
      <BestSeller />
      <BrandSlider />
    </React.Fragment>
  );
}

export default Landing;

