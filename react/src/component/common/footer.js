import React from 'react';

export default function Footer(props) {
    return (
        <>
            <footer class="footer py-md-5 pt-md-3 pb-sm-5">
                
                <div class="container-fluid py-lg-5 mt-sm-5">
                    <div class="row p-sm-4 px-3 py-5">
                        <div class="col-lg-4 col-md-6 footer-top mt-lg-0 mt-md-5">
                            <h2>
                                <a href="index.html" class="text-theme text-uppercase">
                                    {localStorage.getItem('company')}
                                </a>
                            </h2>
                            <p class="mt-2">Chto tho Donec consequat sam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus
                            id quod possimusapien ut leo cursus rhoncus. Nullam dui mi, vulputate ac metus at, semper varius
                            orci.
                    </p>
                        </div>
                        <div class="col-lg-2  col-md-6 mt-lg-0 mt-5">
                            <div class=".footerv2-w3ls">
                                <h3 class="mb-3 w3f_title">Navigation</h3>
                                <hr />
                                <ul class="list-agileits">
                                    <li>
                                        <a href="index.html">
                                            Home
                                </a>
                                    </li>
                                    <li class="my-2">
                                        <a href="about.html">
                                            About Us
                                </a>
                                    </li>
                                    <li class="my-2">
                                        <a href="gallery.html">
                                            Gallery
                                </a>
                                    </li>
                                    <li class="mb-2">
                                        <a href="services.html">
                                            Services
                                </a>
                                    </li>
                                    <li>
                                        <a href="contact.html">
                                            Contact Us
                                </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 mt-lg-0 mt-5">
                            <div class="footerv2-w3ls">
                                <h3 class="mb-3 w3f_title">Contact Us</h3>
                                <hr />
                                <div class="fv3-contact">
                                    <span class="fas fa-envelope-open mr-2"></span>
                                    <p>
                                        <a href="mailto:example@email.com">info@example.com</a>
                                    </p>
                                </div>
                                <div class="fv3-contact my-2">
                                    <span class="fas fa-phone-volume mr-2"></span>
                                    <p>+456 123 7890</p>
                                </div>
                                <div class="fv3-contact">
                                    <span class="fas fa-home mr-2"></span>
                                    <p>+90 nsequursu dsdesdc,
                                <br />xxx Street State 34789.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 mt-lg-0 mt-5">
                            <div class="footerv2-w3ls">
                                <h3 class="mb-3 w3f_title">Links</h3>
                                <hr />
                                <ul class="list-agileits">
                                    <li>
                                        <a href="index.html">
                                            Overview
                                </a>
                                    </li>
                                    <li class="my-2">
                                        <a href="services.html">
                                            Centres of Excellence
                                </a>
                                    </li>
                                    <li class="my-2">
                                        <a href="single.html">
                                            Blog
                                </a>
                                    </li>
                                    <li class="mb-2">
                                        <a href="contact.html">
                                            Find us
                                </a>
                                    </li>
                                    <li>
                                        <a href="index.html">
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
            <div class="cpy-right text-center">
                <p>© {new Date().getFullYear()} {localStorage.getItem('company')}. All rights reserved | Developed by
                    <a href="http://w3layouts.com"> {localStorage.getItem('author')}</a>
                </p>
            </div>
        </>
    );
}