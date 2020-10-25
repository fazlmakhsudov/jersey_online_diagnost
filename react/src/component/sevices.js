import React from 'react';
import InnerBanner from './common/inner-banner';
import BreadCrump from './common/bread-crump';
import Agileits from './common/agileits';
import AgileitServices from './common/agileit-services';
import Footer from './common/footer';

export default function Sevices(props) {
    return (
        <div>
            <InnerBanner menu='services' />
            <BreadCrump menu='services' />
            <Agileits />
            <AgileitServices />
            <Footer />
        </div>
    );
}
