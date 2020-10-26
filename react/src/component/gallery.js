import React from 'react';
import InnerBanner from './common/inner-banner';
import BreadCrump from './common/bread-crump';
import GalleryItems from './common/gallery-items';
import Footer from './common/footer';

export default function Gallery(props) {
    return (
        <div>
            <InnerBanner menu='gallery' />
            <BreadCrump menu = 'gallery' />
            <GalleryItems />
            <Footer />
        </div>
    );
}