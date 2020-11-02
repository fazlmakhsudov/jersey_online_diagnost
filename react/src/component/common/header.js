import React from 'react';

export default function Header(props) {
    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-light bg-gradient-secondary pt-3">
                <h1 >
                    <a className="navbar-brand text-white" href="/index" >
                        {localStorage.getItem('company')}
                    </a>
                </h1>
                <button className="navbar-toggler ml-md-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation" >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent" style={{ position: 'absolute', left: '50%' }}>
                    {/* <!--style={{ position: 'absolute', left: '50%' }} --> */}
                    <ul className="navbar-nav ml-lg-auto text-center">
                        <li className="nav-item  mr-3 active">
                            <a className="nav-link text-white active" href="/home">Home
                                <span className="sr-only">(current)</span>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white text-capitalize" href="/about">about</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link text-white text-capitalize" href="/services">services</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link  text-white text-capitalize" href="/gallery">gallery</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link  text-white text-capitalize" href="/contact">contact</a>
                        </li>
                        {/* <li className="nav-item">
                            <a className="nav-link  text-white text-capitalize" href="/contact">EN | RU</a>
                        </li> */}
                        <li className="nav-item dropdown">
                            <span className="nav-link dropdown-toggle  text-white" href="/#" id="navbarDropdown" role="button" data-toggle="dropdown"
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
    );
}
