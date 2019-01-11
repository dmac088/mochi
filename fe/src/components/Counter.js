import React, { Component } from 'react';
import PropTypes from 'prop-types';

class Counter extends Component {
	constructor(props){
		super(props)
		this.state = { value: this.props.productQuantity };
	}

	resetQuantity(){
		this.setState({
			value: 1
		})
	}
	render() {
		return (
			<div className="stepper-input">
				<button href="#" className="decrement" onClick={() => {console.log('-')}}>–</button>
				<input ref="feedQty" type="number" className="quantity" value={this.state.value} onChange={() => {console.log(this.state.value)}} />
				<button className="increment" onClick={() => {console.log('+')}}>+</button>
			</div>
		)
	}
}

Counter.propTypes = {
  value: PropTypes.number
};

export default Counter;
