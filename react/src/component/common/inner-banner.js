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
        <div className="inner-banner" id="home">
            <header>
                <nav className="navbar navbar-expand-lg navbar-light bg-gradient-secondary pt-3">

                    <h1>
                        <a className="navbar-brand text-white" href="index">
                            {localStorage.getItem('company')}
                        </a>
                    </h1>
                    <button className="navbar-toggler ml-md-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent" style={{ position: 'absolute', left: '50%' }}>
                        <ul className="navbar-nav ml-lg-auto text-center">
                            <li className="nav-item ">
                                <a className="nav-link text-white" href="/home">Home
                                <span className="sr-only">(current)</span>
                                </a>
                            </li>
                            <li className={makeActive('about')}>
                                <a className="nav-link text-white text-capitalize" href="/about">about</a>
                            </li>
                            <li className={makeActive('services')}>
                                <a className="nav-link text-white text-capitalize" href="/services">services</a>
                            </li>
                            <li className={makeActive('gallery')}>
                                <a className="nav-link  text-white text-capitalize" href="/gallery">gallery</a>
                            </li>
                            <li className={makeActive('contact')}>
                                <a className="nav-link  text-white text-capitalize" href="/contact">contact</a>
                            </li>
                            {/* <li className='nav-item d-inline'>
                                <a className="nav-link  text-white text-capitalize d-inline" href="#">EN</a>
                                <span className="nav-link  text-white text-capitalize d-inline">|</span>
                                <a className="nav-link  text-white text-capitalize d-inline" href="#">RU</a>           
                            </li> */}
                            <li className="nav-item dropdown">
                                <span className="nav-link dropdown-toggle  text-white" id="navbarDropdown" role="button" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                    {
                                        sessionStorage.getItem('token') != null ?
                                            <>
                                                {sessionStorage.getItem('email') + '     '} 
                                                <span className='mdi mdi-account-check' />
                                            </>
                                            : <span className='mdi mdi-account-outline'></span>
                                    }
                                </span>
                                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    {
                                        sessionStorage.getItem('token') === null ?

                                            <>
                                                <a className="dropdown-item" href="/login">Log in</a>
                                                <div className="dropdown-divider"></div>
                                                <a className="dropdown-item" href="/registration">Registration</a>
                                            </>

                                            : sessionStorage.getItem('role') === '3' ?
                                                <>
                                                    <a className="dropdown-item" href="/my-cabinet">My cabinet</a>
                                                    <div className="dropdown-divider"></div>
                                                    <a className="dropdown-item" href="/logout">Log out</a>
                                                </>
                                                : sessionStorage.getItem('role') === '2' ?

                                                    <>
                                                        <a className="dropdown-item" href="/my-cabinet">My cabinet</a>
                                                        <div className="dropdown-divider"></div>
                                                        <a className="dropdown-item" href="/logout">Log out</a>
                                                    </>
                                                    : sessionStorage.getItem('role') === '1' ?

                                                        <>
                                                            <a className="dropdown-item" href="/admin">My admin</a>
                                                            <div className="dropdown-divider"></div>
                                                            <a className="dropdown-item" href="/logout">Log out</a>
                                                        </>
                                                        : <a className="dropdown-item" href="/logout">Log out</a>
                                    }
                                </div>
                            </li>
                        </ul>
                    </div>

                </nav>
            </header>
        </div>
    );
}