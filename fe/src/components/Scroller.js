import React, { Component } from 'react';


class Scroller extends Component {

  constructor(props) {
    super(props);
    this.state = {
        position: 0,
        showScroller: false,
    };
  }

  componentDidMount() {
    window.addEventListener('scroll', this.listenToScroll, { passive: true });
  }

  componentWillUnmount() {
    window.removeEventListener('scroll', this.listenToScroll, { passive: true });
  }

  listenToScroll = () => {
    let scroll = document.documentElement.scrollTop;
    this.setState({
      position: scroll,
    });
  }

  animateScroll = (e) => {
     e.preventDefault();
     const body = document.querySelector('html,body');
     document.querySelector('.scroll-top').onclick = function() {
       Velocity(body, 'scroll', { duration: 1000 });
     };
  }

  render() {
    const { position } = this.state;
    return (
      <React.Fragment>
        <a
          onClick={this.animateScroll}
          href="#"
          className={"scroll-top " + ((position >= 400) ? "fadeIn" : "fadeOut")}
        />
      </React.Fragment>
    )
  }
}

export default Scroller;
