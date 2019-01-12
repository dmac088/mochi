import React from 'react';


const Counter = (props) => {
		return (
			<div className="stepper-input">
				<button className="decrement" onClick={props.decrementQuantity}>–</button>
				<input  type="number" className="quantity" value={props.productQty} onChange={() => {console.log(props.productQty)}} />
				<button className="increment" onClick={props.incrementQuantity}>+</button>
			</div>
		);
}

export default Counter;
