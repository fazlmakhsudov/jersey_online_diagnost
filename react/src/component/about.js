import React from 'react';
import InnerBanner from './common/inner-banner.js';
import BreadCrump from './common/bread-crump.js';
import Section2 from './common/section2.js';
import { LittleAgile } from './common/agileits.js';
import Blog from './common/blog.js';
import Testemonials from './common/testemonials.js';
import Footer from './common/footer.js';

export default function About(props) {
    return (
        <div>
            <InnerBanner menu = 'about'/>
            <BreadCrump menu = 'about'/>
            <Section2 showTitle={true} />
            <div className="agileits-about py-5" id="services">
                <div className="container">
                    <LittleAgile />
                </div>
            </div>
            <Blog/>
            <Testemonials />
            <Footer />
        </div>
    );
}
