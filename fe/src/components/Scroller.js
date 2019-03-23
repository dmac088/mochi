import React, { Component } from 'react';


class Scroller extends Component {

  constructor(props) {
    super(props);
    this.state = {
        "position": 0,
        "showScroller": false,
        "body": null,
    };
  }

  componentDidMount() {
    window.addEventListener('scroll', this.listenToScroll, { passive: true });
    this.setState({
      "body": document.querySelector('html,body')
    });
  }

  componentWillUnmount() {
    window.removeEventListener('scroll', this.listenToScroll, { passive: true });
  }

  listenToScroll = () => {
    const scroll = document.documentElement.scrollTop;
    this.setState({
      position: scroll,
    });
  }

  animate = (e) => {
    e.preventDefault();
    const { body } = this.state;
    Velocity(body, 'scroll', { duration: 1000 });
  }

  render() {
    const { position } = this.state;
    return (
      <React.Fragment>
        <a onClick={this.animate}
           className={"scroll-top " + ((position >= 400) ? "fadeIn" : "fadeOut")}
        />
      </React.Fragment>
    )
  }
}

export default Scroller;
