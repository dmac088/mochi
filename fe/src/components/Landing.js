import React from 'react';
import Products from  './Products';

const Landing = (props) =>  {
  console.log('Landing...');
    return(
        <div>
          <Products
            productsList={props.page.content}
            searchTerm={props.searchTerm}
            addToCart={props.addToCart}
            openModal={props.openModal}
            lang={props.lang}
          />
        </div>
    );
}

export default Landing;
