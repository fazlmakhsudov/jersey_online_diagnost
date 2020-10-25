import React from 'react';
import Header from './common/header';

export default function Banner(props) {
    return (
        <div class="banner" id="home">
            {/* <!-- header --> */}
            <Header />
            {/* <!-- //header --> */}
            <div class="container">
                {/* <!-- banner-text --> */}
                <div class="banner-text">
                    <div class="callbacks_container">
                        <ul class="rslides" id="slider3">
                            <li>
                                <div class="slider-info">
                                    <span class="">providing total</span>
                                    <h3>health care solution</h3>
                                    <a class="btn btn-primary mt-3" href="services.html" role="button">View Details</a>
                                </div>
                            </li>
                            <li>
                                <div class="slider-info">
                                    <span class="">providing total</span>
                                    <h3>health care solution</h3>
                                    <a class="btn btn-primary mt-3" href="services.html" role="button">View Details</a>
                                </div>
                            </li>
                            <li>
                                <div class="slider-info">
                                    <span class="">providing total</span>
                                    <h3>health care solution</h3>
                                    <a class="btn btn-primary mt-3" href="services.html" role="button">View Details</a>
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
