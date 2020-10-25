import React from 'react';

export default function ContactForm(props) {
    return (
        <section class="wthree-row pt-3 pb-lg-5 w3-contact">
            <div class="container py-sm-5 pt-0 pb-5">
                <div class="title-section text-center pb-lg-5">
                    <h4>24/7</h4>
                    <h3 class="w3ls-title text-center text-capitalize">affordable medicare</h3>
                </div>
                <div class="row contact-form pt-lg-5">
                    {/* <!-- contact details --> */}
                    <div class="col-lg-4 contact-bottom mt-lg-0 mt-5">
                        <div class="contact-details-top">
                            <h5 class="sub-title-wthree">contact us</h5>
                            <img src="images/contact.jpg" alt="team-image" class="img-fluid  mb-3" />
                            <p>M Etiam elit elit, elementum sed varius at, adipiscing vitae est.</p>
                        </div>
                        <div class="address">
                            <h5 class="sub-title-wthree">contact info</h5>
                            <div class="row wthree-cicon">
                                <span class="fas fa-envelope-open mr-3"></span>
                                <a href="mailto:info@example.com">mail@polyclinic.com</a>
                            </div>
                            <div class="row wthree-cicon">
                                <span class="fas fa-phone-volume mr-3"></span>
                                <h6>+456 123 7890</h6>
                            </div>
                            <div class="row wthree-cicon">
                                <span class="fas fa-globe mr-3"></span>
                                <a href="#">www.polyclinic.com</a>
                            </div>
                        </div>
                        <div class="footerv2-w3ls">
                            <h5 class="sub-title-wthree">follow us</h5>
                            <ul class="social-iconsv2 agileinfo">
                                <li>
                                    <a href="#">
                                        <i class="fab fa-facebook-f"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fab fa-twitter"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fab fa-google-plus-g"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fab fa-linkedin-in"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    {/* <!-- //contact details --> */}
                    <div class="col-lg-8 wthree-form-left px-lg-5 mt-lg-0 mt-5">
                        {/* <!-- contact form grid --> */}
                        <div class="contact-top1">
                            <h5 class="sub-title-wthree">contact form</h5>
                            <form action="#" method="get" class="pc-contact">
                                <div class="row">
                                    <div class="col-md-6 form-group">
                                        <label for="contact-name">Name
                                        <span>*</span>
                                        </label>
                                        <input type="text" class="form-control" id="contact-name" required />
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label for="contact-email">Email
                                        <span>*</span>
                                        </label>
                                        <input type="email" class="form-control" id="contact-email" required />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="contact-subject">Subject
                                    <span>*</span>
                                    </label>
                                    <input type="text" class="form-control" id="contact-subject" required />
                                </div>
                                <div class="form-group">
                                    <label for="contact-message">
                                        Your Message
                                    <span>*</span>
                                    </label>
                                    <textarea class="form-control" rows="5" id="contact-message" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block w-25">Send</button>
                            </form>
                        </div>
                        {/* <!--  //contact form grid ends here --> */}
                    </div>
                </div>
                {/* <!-- //contact details container --> */}
            </div>
            {/* <!-- contact map grid --> */}
            <div class="map contact-right p-sm-5 p-3 pb-lg-5">
                <div class="title-section text-center pb-5">
                    <h4>world of medicine</h4>
                    <h3 class="w3ls-title text-center text-capitalize">Get directions</h3>
                </div>
                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d3955.098115627792!2d36.247567308463495!3d50.000786248652176!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x4847f00cec333b6!2zTlRVICJIUEki!5e0!3m2!1sen!2sua!4v1598288582945!5m2!1sen!2sua" width="600" height="450" frameborder="0" allowfullscreen="" aria-hidden="false" tabindex="0">

                </iframe>
                {/* <iframe src=""
                    allowfullscreen></iframe> */}
            </div>
            {/* <!--//contact map grid ends here--> */}
        </section>
    );
}
