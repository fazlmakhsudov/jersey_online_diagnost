import React from 'react';

export default function GalleryItems(props) {
    function formGallerItems(range = 1) {
        let items = [];
        for (let i = range; i < range + 3; i++) {
            let url = "images/g" + i + ".jpg";
            let item = <div className="col-sm-4 agile_gallery_grid" key={items}>
                <a title="Start Improving Your Business Today." href={url}>
                    <div className="agile_gallery_grid1">
                        <img src={url} className="img-fluid" alt='' />
                        <div className="w3layouts_gallery_grid1_pos">
                            <h3>{localStorage.getItem('company')}</h3>
                            <p>World of Medicine</p>
                        </div>
                    </div>
                </a>
            </div>;
            items.push(item);
        }
        return items;
    }

    return (
        <div className="gallery  py-5" id="gallery">
            <div className="container py-md-5">
                <div className="title-section text-center pb-5">
                    <h4>world of medicine</h4>
                    <h3 className="w3ls-title text-center text-capitalize">Gallery</h3>
                </div>
                <div className="row w3ls_gallery_grids">
                    <div className="row w3_agile_gallery_grid pt-lg-5">
                        {
                            formGallerItems()
                        }
                    </div>
                    <div className="row w3_agile_gallery_grid">
                        {
                            formGallerItems(4)
                        }
                    </div>
                    <div className="row w3_agile_gallery_grid pb-lg-5">
                        {
                            formGallerItems(7)
                        }
                    </div>
                </div>
            </div>
        </div>
    );
}