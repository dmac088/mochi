import React from 'react';


const Counter = (props) => {
		return (
			<div className="stepper-input">
				<button className="decrement" onClick={() => props.updateQuantity(-1)}>–</button>
				<input  type="number" className="quantity" value={props.productQty} onChange={() => {console.log(props.productQty)}} />
				<button className="increment" onClick={() => props.updateQuantity(1)}>+</button>
			</div>
		);
}

export default Counter;
