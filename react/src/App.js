import React from 'react';
import './App.css';
import Home from './component/home.js';
import Enquire from './component/common/enquire.js';
import Services from './component/sevices.js';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import About from './component/about';
import Contact from './component/contact';
import Gallery from './component/gallery';
import PatientCabinet from './component/patient-cabinet.js';
import DoctorCabinet from './component/doctor-cabinet.js';
import Admin from './admins/app/App.js';
import Login from './component/login.js';
import Registration from './component/registration.js';
import Logout from './component/logout.js';
import Error404 from './component/Error404.js';



export default function App() {
    localStorage.setItem('company', 'Online-Diagnost');
    localStorage.setItem('author', 'Fazliddin Makhsudov');
    localStorage.setItem('logo', 'hospital that you can trust');
   

    return (
        <Router>
            <Switch>
                <Route exact path="/">
                    <Home />
                </Route>
                <Route exact path="/home">
                    <Home />
                </Route>
                <Route path="/about">
                    <About />
                </Route>
                <Route path="/gallery">
                    <Gallery />
                </Route>
                <Route path="/services">
                    <Services />
                </Route>
                <Route path="/contact">
                    <Contact />
                </Route>
                {
                    sessionStorage.getItem('role') != null && sessionStorage.getItem('role') === '3'
                        ?
                        <Route path="/my-cabinet">
                            <PatientCabinet />
                        </Route>
                        : sessionStorage.getItem('role') != null && sessionStorage.getItem('role') === '2'
                            ?
                            <Route path="/my-cabinet">
                                <DoctorCabinet />
                            </Route>
                            : <Route path="/my-cabinet">
                                <Home />
                            </Route>

                }
                <Route path="/my-cabinet">
                    <PatientCabinet />
                </Route>
                <Route path="/login">
                    <Login />
                </Route>
                <Route path="/registration">
                    <Registration />
                </Route>
                <Route path="/logout">
                    <Logout />
                </Route>
                {
                    sessionStorage.getItem('role') != null && sessionStorage.getItem('role') === '1'
                        ?
                        <Route path="/admin">
                            <Router basename="/admin">
                                <Admin />
                            </Router>
                        </Route>
                        : ''

                }
                <Route default >
                    <Error404 />
                </Route>
            </Switch>
            <Enquire />
        </Router>
    );
}


