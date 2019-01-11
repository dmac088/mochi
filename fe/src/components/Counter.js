import React from 'react';
import PropTypes from 'prop-types';

const Counter = (props) => {
		return (
			<div className="stepper-input">
				<button 	className="decrement" onClick={() => {console.log('-')}}>–</button>
				<input  type="number" className="quantity" value={1} onChange={() => {console.log(this.state.value)}} />
				<button className="increment" onClick={() => {console.log('+')}}>+</button>
			</div>
		);
}

export default Counter;
