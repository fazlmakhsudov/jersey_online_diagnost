import React from 'react';
import { Form, Button } from 'react-bootstrap';

export default function ContactForm(props) {
    return (
        <section className="wthree-row pt-3 pb-lg-5 w3-contact">
            <div className="container py-sm-5 pt-0 pb-5">
                <div className="title-section text-center pb-lg-5">
                    <h4>24/7</h4>
                    <h3 className="w3ls-title text-center text-capitalize">affordable medicien</h3>
                </div>
                <div className="row contact-form pt-lg-5">
                    {/* <!-- contact details --> */}
                    <div className="col-lg-4 contact-bottom mt-lg-0 mt-5">
                        <div className="contact-details-top">
                            <h5 className="sub-title-wthree">contact us</h5>
                            <img src="images/contact.jpg" className="img-fluid  mb-3" alt='' />
                            <p>Doc. Anna, therapist, responds in few minutes.</p>
                        </div>
                        <div className="address">
                            <h5 className="sub-title-wthree">contact info</h5>
                            <div className="row wthree-cicon">
                                <span className="fas fa-envelope-open mr-3"></span>
                                <a href="mailto:info@example.com">mail@online-diagnost.com</a>
                            </div>
                            <div className="row wthree-cicon">
                                <span className="fas fa-phone-volume mr-3"></span>
                                <h6>+380 666 66 66</h6>
                            </div>
                            <div className="row wthree-cicon">
                                <span className="fas fa-globe mr-3"></span>
                                <a href="/home">www.online-diagnost.com</a>
                            </div>
                        </div>
                        <div className="footerv2-w3ls">
                            <h5 className="sub-title-wthree">follow us</h5>
                            <ul className="social-iconsv2 agileinfo">
                                <li>
                                    <a href="http://facebook.com">
                                        <i className="fab fa-facebook-f"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="https://twitter.com">
                                        <i className="fab fa-twitter"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="https://www.google.com">
                                        <i className="fab fa-google-plus-g"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="https://linkedin.com">
                                        <i className="fab fa-linkedin-in"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    {/* <!-- //contact details --> */}
                    <div className="col-lg-8 wthree-form-left px-lg-5 mt-lg-0 mt-5">
                        {/* <!-- contact form grid --> */}
                        <div className="contact-top1">
                            <h5 className="sub-title-wthree text-center">request form</h5>
                            <Form className="pc-contact">
                                <Form.Check className="text-center mt-4 mb-3" custom type='checkbox' id={`custom-checkbox1`} label={`Conditional question 1`} />
                                <Form.Check className="text-center mb-3" custom type='checkbox' id={`custom-checkbox2`} label={`Conditional question 2`} />
                                <Form.Check className="text-center mb-3" custom type='checkbox' id={`custom-checkbox3`} label={`Conditional question 3`} />
                                <Form.Check className="text-center mb-3" custom type='checkbox' id={`custom-checkbox4`} label={`Conditional question 4`} />
                                <Form.Check className="text-center mb-3" custom type='checkbox' id={`custom-checkbox5`} label={`Conditional question 5`} />
                                <Form.Check  className="text-center mb-3" custom type='checkbox' id={`custom-checkbox6`} label={`Conditional question 6`} />
                                <div className="text-center"><Button  style={{minWidth:'30%'}}>Send</Button></div>
                            </Form>

                        </div>
                        {/* <!--  //contact form grid ends here --> */}
                    </div>
                </div>
                {/* <!-- //contact details container --> */}
            </div>
            {/* <!-- contact map grid --> */}
            <div className="map contact-right p-sm-5 p-3 pb-lg-5">
                <div className="title-section text-center pb-5">
                    <h4>world of medicine</h4>
                    <h3 className="w3ls-title text-center text-capitalize">Get directions</h3>
                </div>
                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d3955.098115627792!2d36.247567308463495!3d50.000786248652176!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x4847f00cec333b6!2zTlRVICJIUEki!5e0!3m2!1sen!2sua!4v1598288582945!5m2!1sen!2sua"
                    width="600" height="450" frameBorder="0" allowFullScreen="" aria-hidden="false" tabIndex="0" title='Our office'>

                </iframe>
                {/* <iframe src=""
                    allowfullscreen></iframe> */}
            </div>
            {/* <!--//contact map grid ends here--> */}
        </section>
    );
}
