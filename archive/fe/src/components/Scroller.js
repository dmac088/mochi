import React, { Component } from 'react';


class Scroller extends Component {

  constructor(props) {
    super(props);
    this.state = {
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
    const { showScroller } = this.state;
    if(showScroller === (scroll >= 400)) {return;}
    this.setState({
      "showScroller": (scroll >= 400),
    });
  }

  animate = (e) => {
    e.preventDefault();
    const { body } = this.state;
    Velocity(body, 'scroll', { duration: 1000 });
  }

  render() {
    const { showScroller } = this.state;
    return (
      <React.Fragment>
        <a href="#"
           onClick={this.animate}
           className={"scroll-top " + ((showScroller) ? "fadeIn" : "fadeOut")}
        />
      </React.Fragment>
    )
  }
}

export default Scroller;
