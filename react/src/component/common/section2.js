import React from 'react';

export default function Section2(props) {
    return (
        <div className="section-2">
            {
                props.showTitle ?
                    <div className="container">
                        <div className="title-section text-center pb-5">
                            <h4>{localStorage.getItem('company')}</h4>
                            <h3 className="w3ls-title text-center text-capitalize">{localStorage.getItem('logo')}</h3>
                        </div>
                    </div> : ''
            }
            <div className="container-fluid">
                <div className="row slide">
                    <div className="col-lg-4 triple-sec">
                        <h5 className="text-dark">special services</h5>
                        <ul className="list-group mt-3">
                            <li className="list-group-item border-0">
                                <i className="fas fa-heartbeat mr-3"></i>Cras justo odio</li>
                            <li className="list-group-item border-0">
                                <i className="fas fa-user-md mr-3"></i>Dapibus ac facilisis in</li>
                            <li className="list-group-item border-0">
                                <i className="fas fa-pills mr-3"></i>Morbi leo risus</li>
                            <li className="list-group-item border-0">
                                <i className="fas fa-thermometer mr-3"></i>
                            Porta ac consectetur ac</li>
                            <li className="list-group-item border-0">
                                <i className="fas fa-ambulance mr-3"></i>Vestibulum at eros</li>
                        </ul>
                    </div>
                    <div className="col-lg-4  triple-sec">
                        <h5>opening hours</h5>
                        <ul className="list-unstyled">
                            <li className="clearfix py-3">
                                <span className="float-left"> Monday - Friday </span>
                                <div className="value float-right"> 9.00 - 20.00 </div>
                            </li>
                            <li className="clearfix border-top border-bottom my-3 py-3">
                                <span className="float-left"> Saturday </span>
                                <div className="value float-right"> 10.00 - 16.00 </div>
                            </li>
                            <li className="clearfix py-3">
                                <span className="float-left"> Sunday </span>
                                <div className="value float-right"> 9.30 - 18.00 </div>
                            </li>
                        </ul>
                    </div>
                    <div className="col-lg-4 triple-sec">
                        <h5 className="text-black">critical care expertise</h5>
                        <p className="pt-4">ulla quis lorem ut libero malesuada feugiat. Curabitur aliquet quam id dui posuere blandit. Mauris blandit
                        aliquet elit, eget tincidunt nibh pulvinar a. Praesent sapien massa, convallis a pellentesque nec,
                        egestas non nisi. Donec rutrum congue leo eget malesuada.</p>
                        <br />
                        <p>ulla quis lorem ut libero malesuada feugiat. Curabitur aliquet quam id dui posuere blandit. Mauris blandit
                        aliquet elit, eget tincidunt nibh pulvinar a.</p>
                    </div>
                </div>
            </div>
        </div>
    );
}
