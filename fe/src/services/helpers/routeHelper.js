import React from 'react';
import ReactDOM from 'react-dom';
import qs from 'query-string';

export const changeCategory = (e, location, match, history) => {
  e.preventDefault();
  const { search } = location;
  const { locale, currency } = match.params;
  history.push('/'+ locale + '/' + currency + '/category/' + e.currentTarget.id + search);
}

export const changeBrand = (e, location, match, history) => {
  e.preventDefault();
  const { search } = location;
  const { locale, currency, term } = match.params;
  const value = e.currentTarget.id;
  if(value === 'All') {
    history.push('/'+ locale + '/' + currency + '/category/' + term + search);
  } else {
    history.push('/'+ locale + '/' + currency + '/category/' + term + '/brand/' + value + search);
  }
}
