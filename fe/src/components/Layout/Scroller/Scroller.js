import React, { useState, useEffect } from "react";
import Velocity from 'velocity-animate';

function Scroller() {

    const [stateObject, setObjectState] = useState({
        showScroller: false,
        body: null,
    });

    useEffect(() => {
        window.addEventListener('scroll', listenToScroll, { passive: true });
        setObjectState({
            body: document.querySelector('html,body'),
        });
    });

    const listenToScroll = () => {
        const scroll = document.documentElement.scrollTop;
        const { showScroller } = stateObject;
        if (showScroller === (scroll >= 400)) { return; }
        setObjectState({
            showScroller: scroll >= 400,
        });
    }

    const animate = (e) => {
        e.preventDefault();
        const { body } = stateObject;
        Velocity(body, 'scroll', { duration: 1000 });
    }


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
