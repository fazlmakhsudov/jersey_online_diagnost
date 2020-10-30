import React from 'react';

export default function Blog(props) {
    return (
        <section className="blog_w3ls py-lg-5">
            <div className="container">
                <div className="title-section text-center pb-lg-5">
                    <h4>world of medicine</h4>
                    <h3 className="w3ls-title text-center text-capitalize">our blog</h3>
                </div>
                <div className="row py-5">
                    {/* <!-- blog grid --> */}
                    <div className="col-lg-4 col-md-6">
                        <div className="card border-0 med-blog">
                            <div className="card-header p-0">
                                <a href="/home">
                                    <img className="card-img-bottom" src="images/g5.jpg" alt='' />
                                </a>
                            </div>
                            <div className="card-body border-0 px-0">
                                <div className="blog_w3icon">
                                    <span>
                                        May 19,2018 - loremipsum</span>
                                </div>
                                <div className="pt-2">
                                    <h5 className="blog-title card-title font-weight-bold">
                                        <a href="/home">Cras ultricies ligula sed magna dictum porta auris blandita.</a>
                                    </h5>
                                </div>
                                <a href="/home" className="blog-btn">Read more</a>
                            </div>
                        </div>
                    </div>
                    {/* <!-- //blog grid -->
        <!-- blog grid --> */}
                    <div className="col-lg-4 col-md-6 mt-md-0 mt-5">
                        <div className="card border-0 med-blog">
                            <div className="card-header p-0">
                                <a href="/home">
                                    <img className="card-img-bottom" src="images/g2.jpg" alt='' />
                                </a>
                            </div>
                            <div className="card-body border-0 px-0">
                                <div className="blog_w3icon">
                                    <span>
                                        June 21,2018 - loremipsum</span>
                                </div>
                                <div className="pt-2">
                                    <h5 className="blog-title card-title font-weight-bold">
                                        <a href="/home">Cras ultricies ligula sed magna dictum porta auris blandita.</a>
                                    </h5>
                                </div>
                                <a href="/home" className="blog-btn">Read more</a>
                            </div>
                        </div>
                    </div>
                    {/* <!-- //blog grid -->
        <!-- blog grid --> */}
                    <div className="col-lg-4 col-md-6 mt-lg-0 mt-5">
                        <div className="card border-0 med-blog">
                            <div className="card-header p-0">
                                <a href="/home">
                                    <img className="card-img-bottom" src="images/g1.jpg" alt='' />
                                </a>
                            </div>
                            <div className="card-body border-0 px-0">
                                <div className="blog_w3icon">
                                    <span>
                                        July 23,2018 - loremipsum</span>
                                </div>
                                <div className="pt-2">
                                    <h5 className="blog-title card-title font-weight-bold">
                                        <a href="/home">Cras ultricies ligula sed magna dictum porta auris blandita.</a>
                                    </h5>
                                </div>
                                <a href="/home" className="blog-btn">Read more</a>
                            </div>
                        </div>
                    </div>
                    {/* <!-- //blog grid --> */}
                </div>
            </div>
        </section>
    );
}
