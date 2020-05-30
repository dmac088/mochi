import React, { useState, useEffect } from "react";
import Velocity from 'velocity-animate';

function Scroller() {

    const [stateObject, setObjectState] = useState({
        showScroller: false,
        body: null,
    });

    const listenToScroll = () => {
        const scroll = document.documentElement.scrollTop;
        const { showScroller } = stateObject;
        if (showScroller === (scroll >= 400)) { return; }
        setObjectState({
            showScroller: scroll >= 400,
            body: document.querySelector('html,body'),
        });
    }

    const animate = (e) => {
        e.preventDefault();
        const { body } = stateObject;
        Velocity(body, 'scroll', { duration: 1000 });
    }

    useEffect(() => {
        // initiate the event handler
        window.addEventListener('scroll', listenToScroll, { passive: true });
        
        // this will clean up the event every time the component is re-rendered
        return function cleanup() {
            window.removeEventListener('scroll', listenToScroll, { passive: true });
        };
      });

    return (
        <React.Fragment>
            <a href="#"
                onClick={animate}
                className={"scroll-top " + ((stateObject.showScroller) ? "fadeIn" : "fadeOut")}
            />
        </React.Fragment>
    )
}

export default Scroller;
