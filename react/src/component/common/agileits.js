import React from 'react';

export default function Agileits(props) {
    return (
        <div className="agileits-about py-md-5 py-5" id="services">
            <div className="container py-lg-5">
                <div className="title-section text-center pb-md-5">
                    <h4>{localStorage.getItem('company')}</h4>
                    <h3 className="w3ls-title text-center text-capitalize">hospital that you can trust</h3>
                </div>
                <LittleAgile />
            </div>
        </div>
    );
}

export function LittleAgile() {
    return (
        <>
            <div className="agileits-about-row row  text-center pt-md-0 pt-5">
                <div className="col-lg-4 col-sm-6 agileits-about-grids">
                    <div className="p-md-5 p-sm-3">
                        <i className="fas fa-user-md"></i>
                        <h4 className="mt-2 mb-3">therapist</h4>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur aut</p>
                    </div>
                </div>
                <div className="col-lg-4 col-sm-6 agileits-about-grids  border-left border-right my-sm-0 my-5">
                    <div className="p-md-5 p-sm-3">
                        <i className="fas fa-thermometer"></i>
                        <h4 className="mt-2 mb-3">laboratory</h4>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur aut</p>
                    </div>
                </div>
                <div className="col-lg-4 agileits-about-grids">
                    <div className="p-md-5 p-sm-3">
                        <i className="far fa-hospital"></i>
                        <h4 className="mt-2 mb-3">surgery</h4>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur aut</p>
                    </div>
                </div>
            </div>
            <div className="agileits-about-row border-top row text-center pb-lg-5 pt-md-0 pt-5 mt-md-0 mt-5">
                <div className="col-lg-4 col-sm-6 agileits-about-grids">
                    <div className="p-md-5 p-sm-3 col-label">
                        <i className="fas fa-hospital-symbol"></i>
                        <h4 className="mt-2 mb-3">transplants</h4>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur aut</p>
                    </div>
                </div>
                <div className="col-lg-4 col-sm-6 agileits-about-grids mt-lg-0 mt-md-3 border-left border-right pt-sm-0 pt-5">
                    <div className="p-md-5 p-sm-3 col-label">
                        <i className="fas fa-ambulance">
                        </i>
                        <h4 className="mt-2 mb-3">emergency care</h4>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur aut</p>
                    </div>
                </div>
                <div className="col-lg-4 agileits-about-grids pt-md-0 pt-5">
                    <div className="p-md-5 p-sm-3 col-label">
                        <i className="fa fa-user-md"></i>
                        <h4 className="mt-2 mb-3">oncology</h4>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur aut</p>
                    </div>
                </div>
            </div>
        </>
    )
} 
