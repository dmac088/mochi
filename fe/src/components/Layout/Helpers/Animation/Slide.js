import ReactDOM from 'react-dom';
import Velocity from 'velocity-animate';

export const slide = (container, direction, params = { duration: 500 }) => {
    if(!container) { return }
    const element = ReactDOM.findDOMNode(container);
    if (element === undefined) { return; }
    Velocity(element, direction, params);
}
