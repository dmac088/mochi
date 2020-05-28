import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';

export const slide = (container, direction, params = { duration: 500 }) => {
    const element = ReactDOM.findDOMNode(container);
    if (element === undefined) { return; }
    Velocity(element, direction, params);
}