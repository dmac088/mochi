import React from 'react';
import ReactDOM from 'react-dom';
import qs from 'query-string';

export const createRouteProps = (history, match, location) => {
    return {"history":{...history}, "match":{...match}, "location":{...location}};
}

export const homeRouteString = (match) => {
  const { locale, currency } = match.params;
  return '/'+ locale + '/' + currency;
}

export const routeHome = (e, routeProps) => {
  if (e) { e.preventDefault() }
  routeProps.history.push(homeRouteString(routeProps.match));
}

export const routePage = (e, routeProps, page) => {
  e.preventDefault();
  console.log(homeRouteString(routeProps.match) + page);
  routeProps.history.push(homeRouteString(routeProps.match) + page);
}

export const routeLogin = (e, routeProps) => {
   routePage(e, routeProps, '/Auth');
}

export const routeContact = (e, routeProps) => {
  routePage(e, routeProps, '/Contact');
}

export const routeCheckout = (e, routeProps) => {
  routePage(e, routeProps, '/Checkout');
}

export const routeAccount = (e, routeProps, authenticated) => {
  if (authenticated) {
    routePage(e, routeProps, '/Account');
  } else {
    routePage(e, routeProps, '/Auth');
  }
}

export const routeWishlist = (e, routeProps) => {
  routePage(e, routeProps, '/Wishlist');
}

export const routeCart = (e, routeProps) => {
  routePage(e, routeProps, '/Cart');
}

export const routeSearch = (e, routeProps, term) => {
  if(!e) {return}
  e.preventDefault();
  routePage(e, routeProps, '/search/' + ((!term) ? "" : term));
}

export const changeCategory = (e, routeProps) => {
  e.preventDefault();
  const { search } = routeProps.location;
  routeProps.history.push(homeRouteString(routeProps.match) + '/category/' + e.currentTarget.id + search);
}

export const routeSingleProduct = (e, categoryDesc, routeProps) => {
  if(categoryDesc) {
    routePage(e, routeProps, '/category/' + ((!categoryDesc) ? 'ALL' : categoryDesc) + '/product/' + e.currentTarget.id);
  } else {
    routePage(e, routeProps, '/product/' + e.currentTarget.id);
  }
}
