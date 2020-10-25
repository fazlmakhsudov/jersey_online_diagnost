import React from 'react';
import Footer from './common/footer.js';
import Banner from './banner.js';
import Section2 from './common/section2.js';
import Agileits from './common/agileits';
import Blog from './common/blog';
import Testemonials from './common/testemonials';

export default function Home(props) {
    return (
        <div>
            <Banner />
            <Section2 />
            <Agileits />
            <Blog />
            <Testemonials />
            <Footer />
        </div>
    );
}