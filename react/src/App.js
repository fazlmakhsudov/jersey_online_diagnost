import React from 'react';
import './App.css';
import Home from './component/home.js';
import Enquire from './component/common/enquire.js';
import Services from './component/sevices.js';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import About from './component/about';
import Contact from './component/contact';
import Gallery from './component/gallery';
import MyCabinet from './component/my-cabinet.js';
import Admin from './admins/app/App.js';
import Login from './component/login.js';
import Registration from './component/registration.js';



export default function App() {
    localStorage.setItem('company', 'Online-Diagnost');
    localStorage.setItem('author', 'Fazliddin Makhsudov');
    localStorage.setItem('logo', 'hospital that you can trust');
    return (
        <Router>
            <Switch>
                <Route exact path="/index.html">
                    <Home />
                </Route>
                <Route path="/about.html">
                    <About />
                </Route>
                <Route path="/gallery.html">
                    <Gallery />
                </Route>
                <Route path="/services.html">
                    <Services />
                </Route>
                <Route path="/contact.html">
                    <Contact />
                </Route>
                <Route path="/my-cabinet.html">
                    <MyCabinet />
                </Route>
                <Route path="/login.html">
                    <Login />
                </Route>
                <Route path="/registration.html">
                    <Registration />
                </Route>
                <Route path="/admin.html">
                    <Router basename="/admin">
                        <Admin />
                    </Router>
                </Route>
                <Route default >
                    <Home />
                </Route>
            </Switch>
            <Enquire />
        </Router>
    );
}


