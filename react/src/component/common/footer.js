import React from 'react';

export default function Footer(props) {
    return (
        <>
            <footer className="footer py-md-5 pt-md-3 pb-sm-5">

                <div className="container-fluid py-lg-5 mt-sm-5">
                    <div className="row p-sm-4 px-3 py-5">
                        <div className="col-lg-4 col-md-6 footer-top mt-lg-0 mt-md-5">
                            <h2>
                                <a href="/home" className="text-theme text-uppercase">
                                    {localStorage.getItem('company')}
                                </a>
                            </h2>
                            <p className="mt-2">Chto tho Donec consequat sam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus
                            id quod possimusapien ut leo cursus rhoncus. Nullam dui mi, vulputate ac metus at, semper varius
                            orci.
                    </p>
                        </div>
                        <div className="col-lg-2  col-md-6 mt-lg-0 mt-5">
                            <div className=".footerv2-w3ls">
                                <h3 className="mb-3 w3f_title">Navigation</h3>
                                <hr />
                                <ul className="list-agileits">
                                    <li>
                                        <a href="/home">
                                            Home
                                </a>
                                    </li>
                                    <li className="my-2">
                                        <a href="/about">
                                            About Us
                                </a>
                                    </li>
                                    <li className="my-2">
                                        <a href="/gallery">
                                            Gallery
                                </a>
                                    </li>
                                    <li className="mb-2">
                                        <a href="/services">
                                            Services
                                </a>
                                    </li>
                                    <li>
                                        <a href="/contact">
                                            Contact Us
                                </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-lg-3 col-md-6 mt-lg-0 mt-5">
                            <div className="footerv2-w3ls">
                                <h3 className="mb-3 w3f_title">Contact Us</h3>
                                <hr />
                                <div className="fv3-contact">
                                    <span className="fas fa-envelope-open mr-2"></span>
                                    <p>
                                        <a href="/mailto:example@email.com">info@online-diagnost.com</a>
                                    </p>
                                </div>
                                <div className="fv3-contact my-2">
                                    <span className="fas fa-phone-volume mr-2"></span>
                                    <p>+380 66 666 66 66</p>
                                </div>
                                <div className="fv3-contact">
                                    <span className="fas fa-home mr-2"></span>
                                    <p>+90 nsequursu dsdesdc,
                                <br />xxx Street State 34789.</p>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-3 col-md-6 mt-lg-0 mt-5">
                            <div className="footerv2-w3ls">
                                <h3 className="mb-3 w3f_title">Links</h3>
                                <hr />
                                <ul className="list-agileits">
                                    <li>
                                        <a href="/index">
                                            Overview
                                </a>
                                    </li>
                                    <li className="my-2">
                                        <a href="/services">
                                            Centres of Excellence
                                </a>
                                    </li>
                                    <li className="my-2">
                                        <a href="/single">
                                            Blog
                                </a>
                                    </li>
                                    <li className="mb-2">
                                        <a href="/contact">
                                            Find us
                                </a>
                                    </li>
                                    <li>
                                        <a href="/index">
                                            Privacy Policy
                                </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                {/* <!-- //footer bottom --> */}
            </footer>
            <div className="cpy-right text-center">
                <p>© {new Date().getFullYear()} {localStorage.getItem('company')}. All rights reserved | Developed by
                    <span className='text-white'> {localStorage.getItem('author')}</span>
                </p>
            </div>
        </>
    );
}