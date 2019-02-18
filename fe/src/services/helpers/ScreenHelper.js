import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';
import qs from 'query-string';

export const isMobile = () => {
  return  ((window.innerWidth
            || document.documentElement.clientWidth
            || document.body.clientWidth) <= 991);
}


export const slide = (container, direction, params = { duration: 500}, callback) => {
  let element = ReactDOM.findDOMNode(container);
  if(element === undefined) {return}
  Velocity(element, direction, params).then(callback);
}

export const updateParams = (search, params) => {
  let urlParams = (qs.parse(search));
  let mergedParams = Object.assign(urlParams, params);
  return qs.stringify(mergedParams);
}
