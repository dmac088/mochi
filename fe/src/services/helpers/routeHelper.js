import React from 'react';
import ReactDOM from 'react-dom';
import qs from 'query-string';

export const homeRouteString = (match) => {
  const { locale, currency } = match.params;
  return '/'+ locale + '/' + currency;
}

export const routeHome = (e, match, history) => {
  if (e) { e.preventDefault() }
  history.push(homeRouteString(match));
}

export const routePage = (e, match, history, page) => {
  e.preventDefault();
  console.log(homeRouteString(match) + page);
  history.push(homeRouteString(match) + page);
}

export const routeLogin = (e, match, history) => {
   routePage(e, match, history, '/Auth');
}

export const routeContact = (e, match, history) => {
  routePage(e, match, history, '/Contact');
}

export const routeCheckout = (e, match, history) => {
  routePage(e, match, history, '/Checkout');
}

export const routeAccount = (e, match, history, authenticated) => {
  if (authenticated) {
    routePage(e, match, history, '/Account');
  } else {
    routePage(e, match, history, '/Auth');
  }
}

export const routeWishlist = (e, match, history) => {
  routePage(e, match, history, '/Wishlist');
}

export const routeCart = (e, match, history) => {
  routePage(e, match, history, '/Cart');
}

export const routeSearch = (e, match, history, term) => {
  if(!e) {return}
  e.preventDefault();
  routePage(e, match, history, '/search/' + ((!term) ? "" : term));
}

export const changeCategory = (e, location, match, history) => {
  e.preventDefault();
  const { search } = location;
  history.push(homeRouteString(match) + '/category/' + e.currentTarget.id + search);
}

export const changeBrand = (e, location, match, history) => {
  e.preventDefault();
  const { search } = location;
  const { locale, currency, term } = match.params;
  const value = e.currentTarget.id;
  if(value === 'All') {
    history.push(homeRouteString(match) + '/category/' + term + search);
  } else {
    history.push(homeRouteString(match) + '/category/' + term + '/brand/' + value + search);
  }
}

export const routeSingleProduct = (e, match, history, category) => {
  e.preventDefault();
  routePage(e, match, history, '/category/' + ((!category.categoryDesc) ? 'ALL' : category.categoryDesc) + '/product/' + e.currentTarget.id);
}
