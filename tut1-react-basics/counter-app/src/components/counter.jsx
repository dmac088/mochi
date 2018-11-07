import React, { Component } from 'react';

class Counter extends Component {
  state = {
    count: 0,
    tags: ['tag1', 'tag2', 'tag3']
  };

  handleIncrement = product => {
    console.log(product);
    this.setState({count: this.state.count + 1});
  }

  handleDecrement = product => {
    console.log(product);
    this.setState({count: this.state.count - 1});
  }

  render () {
    return  (
            <div>
              <span className={this.getBadgeClasses()}>{this.formatCount()}</span>
              <button
                onClick={(product) => this.handleIncrement(product)}
                className="btn btn-secondary btn-sm">
                  Increment
              </button>

              <button
                onClick={(product) => this.handleDecrement(product)}
                className="btn btn-secondary btn-sm ">
                  Decrement
              </button>
            </div>
    );
  }

  getBadgeClasses() {
    let classes = "badge m-2 ";
    classes += (this.state.count === 0) ? "badge-warning" : "badge-primary";
    return classes;
  }

  formatCount() {
    const { count } = this.state;
    return count === 0 ? "Zero" : count;

  }
}
export default Counter;
