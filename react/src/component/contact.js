import React from 'react';
import InnerBanner from './common/inner-banner';
import BreadCrump from './common/bread-crump';
import ContactForm from './common/contact-form';
import Footer from './common/footer';

export default function Contact(props) {
    return (
        <div>
            <InnerBanner menu='contact' />
            <BreadCrump menu='contact' />
            <ContactForm />
            <Footer />
        </div>
    );
}