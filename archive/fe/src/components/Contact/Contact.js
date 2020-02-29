import React from 'react';
import { google } from 'google-maps';



  const mapInit = () => {
        // Basic options for a simple Google Map
        // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
        let mapOptions = {
            // How zoomed in you want the map to start at (always required)
            zoom: 12,

            scrollwheel: false,

            // The latitude and longitude to center the map (always required)
            center: new google.maps.LatLng(40.740610, -73.935242), // New York

            // How you would like to style the map.
            // This is where you would paste any style found on

            styles: [{
                    "featureType": "water",
                    "elementType": "geometry",
                    "stylers": [{
                            "color": "#e9e9e9"
                        },
                        {
                            "lightness": 17
                        }
                    ]
                },
                {
                    "featureType": "landscape",
                    "elementType": "geometry",
                    "stylers": [{
                            "color": "#f5f5f5"
                        },
                        {
                            "lightness": 20
                        }
                    ]
                },
                {
                    "featureType": "road.highway",
                    "elementType": "geometry.fill",
                    "stylers": [{
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 17
                        }
                    ]
                },
                {
                    "featureType": "road.highway",
                    "elementType": "geometry.stroke",
                    "stylers": [{
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 29
                        },
                        {
                            "weight": 0.2
                        }
                    ]
                },
                {
                    "featureType": "road.arterial",
                    "elementType": "geometry",
                    "stylers": [{
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 18
                        }
                    ]
                },
                {
                    "featureType": "road.local",
                    "elementType": "geometry",
                    "stylers": [{
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 16
                        }
                    ]
                },
                {
                    "featureType": "poi",
                    "elementType": "geometry",
                    "stylers": [{
                            "color": "#f5f5f5"
                        },
                        {
                            "lightness": 21
                        }
                    ]
                },
                {
                    "featureType": "poi.park",
                    "elementType": "geometry",
                    "stylers": [{
                            "color": "#dedede"
                        },
                        {
                            "lightness": 21
                        }
                    ]
                },
                {
                    "elementType": "labels.text.stroke",
                    "stylers": [{
                            "visibility": "on"
                        },
                        {
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 16
                        }
                    ]
                },
                {
                    "elementType": "labels.text.fill",
                    "stylers": [{
                            "saturation": 36
                        },
                        {
                            "color": "#333333"
                        },
                        {
                            "lightness": 40
                        }
                    ]
                },
                {
                    "elementType": "labels.icon",
                    "stylers": [{
                        "visibility": "off"
                    }]
                },
                {
                    "featureType": "transit",
                    "elementType": "geometry",
                    "stylers": [{
                            "color": "#f2f2f2"
                        },
                        {
                            "lightness": 19
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "geometry.fill",
                    "stylers": [{
                            "color": "#fefefe"
                        },
                        {
                            "lightness": 20
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "geometry.stroke",
                    "stylers": [{
                            "color": "#fefefe"
                        },
                        {
                            "lightness": 17
                        },
                        {
                            "weight": 1.2
                        }
                    ]
                }
            ]
        };

        // Get the HTML DOM element that will contain your map
        // We are using a div with id="map" seen below in the <body>
        let mapElement = document.getElementById('google-map');

        // Create the Google Map using our element and options defined above
        let map = new google.maps.Map(mapElement, mapOptions);

        // Let's also add a marker while we're at it
        let marker = new google.maps.Marker({
            position: new google.maps.LatLng(40.740610, -73.935242),
            map: map,
            title: 'Greenfarm',
            icon: "assets/images/icons/map-marker.png",
            animation: google.maps.Animation.BOUNCE
        });

  }

  export const Contact = (props) => {
			return(
          <div className="page-content mb-50">

            <div className="google-map-container mb-70">
              <div id="google-map"></div>
            </div>


            <div className="container">
              <div className="row">
                <div className="col-lg-3 col-md-4 mb-xs-35">
                  <div className="contact-page-side-content">
                    <h3 className="contact-page-title">Contact Us</h3>

                    <div className="single-contact-block">
                      <h4><img src="assets/images/icons/contact-icon1.png" alt="" /> Address</h4>
                      <p>123 Main Street, Anytown, CA 12345 – USA</p>
                    </div>

                    <div className="single-contact-block">
                      <h4><img src="assets/images/icons/contact-icon2.png" alt="" /> Phone</h4>
                      <p>Mobile: (08) 123 456 789</p>
                      <p>Hotline: 1009 678 456</p>
                    </div>

                    <div className="single-contact-block">
                      <h4><img src="assets/images/icons/contact-icon3.png" alt="" /> Email</h4>
                      <p>yourmail@domain.com</p>
                      <p>support@hastech.company</p>
                    </div>

                  </div>

                </div>
                <div className="col-lg-9 col-md-8 pl-100 pl-xs-15">
                  <div className="contact-form-content">
                    <h3 className="contact-page-title">Tell Us Your Message</h3>

                    <div className="contact-form">
                      <form  id="contact-form" action="assets/php/mail.php" method="post">
                        <div className="form-group">
                          <label>Your Name <span className="required">*</span></label>
                          <input type="text" name="customerName" id="customername" required />
                        </div>
                        <div className="form-group">
                          <label>Your Email <span className="required">*</span></label>
                          <input type="email" name="customerEmail" id="customerEmail" required />
                        </div>
                        <div className="form-group">
                          <label>Subject</label>
                          <input type="text" name="contactSubject" id="contactSubject" />
                        </div>
                        <div className="form-group">
                          <label>Your Message</label>
                          <textarea name="contactMessage" id="contactMessage" ></textarea>
                        </div>
                        <div className="form-group">
                          <button type="submit" value="submit" id="submit" className="contact-form-btn" name="submit">send</button>
                        </div>
                      </form>
                    </div>
                    <p className="form-messege pt-10 pb-10 mt-10 mb-10"></p>
                  </div>

                </div>
              </div>
            </div>
          </div>
        )
      }
