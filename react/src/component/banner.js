import React from 'react';
import Header from './common/header';

export default function Banner(props) {
    return (
        <div className="banner" id="home">
            {/* <!-- header --> */}
            <Header />
            {/* <!-- //header --> */}
            <div className="container">
                {/* <!-- banner-text --> */}
                <div className="banner-text">
                    <div className="callbacks_container">
                        <ul className="rslides" id="slider3">
                            <li>
                                <div className="slider-info">
                                    <span className="">providing total</span>
                                    <h3>health care solution</h3>
                                    <a className="btn btn-primary mt-3" href="services.html" role="button">View Details</a>
                                </div>
                            </li>
                            <li>
                                <div className="slider-info">
                                    <span className="">providing total</span>
                                    <h3>health care solution</h3>
                                    <a className="btn btn-primary mt-3" href="services.html" role="button">View Details</a>
                                </div>
                            </li>
                            <li>
                                <div className="slider-info">
                                    <span className="">providing total</span>
                                    <h3>health care solution</h3>
                                    <a className="btn btn-primary mt-3" href="services.html" role="button">View Details</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            {/* <!-- //container --> */}
        </div>
    );
}
