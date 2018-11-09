import React, { Component } from 'react';

class Counter extends Component {
  state = {
    value: this.props.value
  };

  handleIncrement = product => {
    console.log(product);
    this.setState({value: this.state.value + 1});
  }

  handleDecrement = product => {
    console.log(product);
    this.setState({value: this.state.value - 1});
  }

  render () {

    return  (
            <div>
              <span className={this.getBadgeClasses()}>{this.formatValue()}</span>
              <button
                onClick={(product) => this.handleIncrement(product)}
                className="btn btn-secondary btn-sm m-2">
                  Increment
              </button>

              <button
                onClick={(product) => this.handleDecrement(product)}
                className="btn btn-secondary btn-sm m-2">
                  Decrement
              </button>

              <button onClick={this.props.onDelete} className="btn btn-danger btn-sm m-2">
                Delete
              </button>
            </div>
    );
  }

  onDelete() {

  }

  getBadgeClasses() {
    let classes = "badge m-2 ";
    classes += (this.state.value === 0) ? "badge-warning" : "badge-primary";
    return classes;
  }

  formatValue() {
    const { value } = this.state;
    return value === 0 ? "Zero" : value;

  }
}
export default Counter;
