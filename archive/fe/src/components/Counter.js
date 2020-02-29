import React from 'react';


const Counter = (props) => {
		return (
			<div className="stepper-input">
				<button id={props.productId} className="decrement" onClick={props.decrementQuantity}>–</button>
				<input  type="number" className="quantity" value={props.productQty} onChange={() => {}} />
				<button id={props.productId} className="increment" onClick={props.incrementQuantity}>+</button>
			</div>
		);
}

export default Counter;
