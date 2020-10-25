import React from 'react';

export default function Blog(props) {
    return (
        <section class="blog_w3ls py-lg-5">
            <div class="container">
                <div class="title-section text-center pb-lg-5">
                    <h4>world of medicine</h4>
                    <h3 class="w3ls-title text-center text-capitalize">our blog</h3>
                </div>
                <div class="row py-5">
                    {/* <!-- blog grid --> */}
                    <div class="col-lg-4 col-md-6">
                        <div class="card border-0 med-blog">
                            <div class="card-header p-0">
                                <a href="single.html">
                                    <img class="card-img-bottom" src="images/g5.jpg" alt="Card image cap" />
                                </a>
                            </div>
                            <div class="card-body border-0 px-0">
                                <div class="blog_w3icon">
                                    <span>
                                        May 19,2018 - loremipsum</span>
                                </div>
                                <div class="pt-2">
                                    <h5 class="blog-title card-title font-weight-bold">
                                        <a href="single.html">Cras ultricies ligula sed magna dictum porta auris blandita.</a>
                                    </h5>
                                </div>
                                <a href="single.html" class="blog-btn">Read more</a>
                            </div>
                        </div>
                    </div>
                    {/* <!-- //blog grid -->
        <!-- blog grid --> */}
                    <div class="col-lg-4 col-md-6 mt-md-0 mt-5">
                        <div class="card border-0 med-blog">
                            <div class="card-header p-0">
                                <a href="single.html">
                                    <img class="card-img-bottom" src="images/g2.jpg" alt="Card image cap" />
                                </a>
                            </div>
                            <div class="card-body border-0 px-0">
                                <div class="blog_w3icon">
                                    <span>
                                        June 21,2018 - loremipsum</span>
                                </div>
                                <div class="pt-2">
                                    <h5 class="blog-title card-title font-weight-bold">
                                        <a href="single.html">Cras ultricies ligula sed magna dictum porta auris blandita.</a>
                                    </h5>
                                </div>
                                <a href="single.html" class="blog-btn">Read more</a>
                            </div>
                        </div>
                    </div>
                    {/* <!-- //blog grid -->
        <!-- blog grid --> */}
                    <div class="col-lg-4 col-md-6 mt-lg-0 mt-5">
                        <div class="card border-0 med-blog">
                            <div class="card-header p-0">
                                <a href="single.html">
                                    <img class="card-img-bottom" src="images/g1.jpg" alt="Card image cap" />
                                </a>
                            </div>
                            <div class="card-body border-0 px-0">
                                <div class="blog_w3icon">
                                    <span>
                                        July 23,2018 - loremipsum</span>
                                </div>
                                <div class="pt-2">
                                    <h5 class="blog-title card-title font-weight-bold">
                                        <a href="single.html">Cras ultricies ligula sed magna dictum porta auris blandita.</a>
                                    </h5>
                                </div>
                                <a href="single.html" class="blog-btn">Read more</a>
                            </div>
                        </div>
                    </div>
                    {/* <!-- //blog grid --> */}
                </div>
            </div>
        </section>
    );
}
