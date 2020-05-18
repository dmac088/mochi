import React from 'react';


function Contact() {
  return (
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
                <form id="contact-form" action="assets/php/mail.php" method="post">
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

export default Contact;