import React from 'react';

export default function InnerBanner(props) {


    function makeActive(menuItem = 'home') {
        let navStyle = "nav-item  mr-3 ";
        if (Object.is(props.menu, menuItem)) {
            navStyle += Object.is(props.menu, 'dropdown') ? "dropdown active" : 'active';
        }
        return navStyle;
    }


    return (
        <div class="inner-banner" id="home">
            <header>
                <nav class="navbar navbar-expand-lg navbar-light bg-gradient-secondary pt-3">

                    <h1>
                        <a class="navbar-brand text-white" href="index.html">
                            {localStorage.getItem('company')}
                        </a>
                    </h1>
                    <button class="navbar-toggler ml-md-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent" style={{ position: 'absolute', left: '55%' }}>
                        <ul class="navbar-nav ml-lg-auto text-center">
                            <li class="nav-item ">
                                <a class="nav-link text-white" href="index.html">Home
                                <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class={makeActive('about')}>
                                <a class="nav-link text-white text-capitalize" href="about.html">about</a>
                            </li>
                            <li class={makeActive('services')}>
                                <a class="nav-link text-white text-capitalize" href="services.html">services</a>
                            </li>
                            <li class={makeActive('gallery')}>
                                <a class="nav-link  text-white text-capitalize" href="gallery.html">gallery</a>
                            </li>
                            <li class={makeActive('contact')}>
                                <a class="nav-link  text-white text-capitalize" href="contact.html">contact</a>
                            </li>
                            {/* <li class='nav-item d-inline'>
                                <a class="nav-link  text-white text-capitalize d-inline" href="#">EN</a>
                                <span class="nav-link  text-white text-capitalize d-inline">|</span>
                                <a class="nav-link  text-white text-capitalize d-inline" href="#">RU</a>           
                            </li> */}
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle  text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                    fazl@gmail.com
                            </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="my-cabinet.html">My cabinet</a>
                                    <a class="dropdown-item" href="#">something</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Log out</a>
                                </div>
                            </li>
                        </ul>
                    </div>

                </nav>
            </header>
        </div>
    );
}