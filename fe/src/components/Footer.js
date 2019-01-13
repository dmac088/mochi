import React from 'react';

const Footer = (props) =>{
    return(
        <footer>
            <p className="footer-links">
                <a href="https://github.com/sivadass/react-shopping-cart" rel="noopener noreferrer" target="_blank">View Source on Github</a>
                <span>  /  </span>
                <a href="mailto:contact@sivadass.in" rel="noopener noreferrer" target="_blank">Need any help?</a>
                <span>  /  </span>
                <a href="https://twitter.com/NSivadass" rel="noopener noreferrer" target="_blank">Say Hi on Twitter</a>
                <span>  /  </span>
                <a href="https://sivadass.in" rel="noopener noreferrer" target="_blank">Read My Blog</a>
            </p>
            <p>&copy; 2017 <strong>Veggy</strong> - Organic Green Store</p>
        </footer>
    )
};

export default Footer;
