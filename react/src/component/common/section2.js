import React from 'react';

export default function Section2(props) {
    return (
        <div class="section-2">
            {
                props.showTitle ?
                    <div class="container">
                        <div class="title-section text-center pb-5">
                            <h4>{localStorage.getItem('company')}</h4>
                            <h3 class="w3ls-title text-center text-capitalize">{localStorage.getItem('logo')}</h3>
                        </div>
                    </div> : ''
            }
            <div class="container-fluid">
                <div class="row slide">
                    <div class="col-lg-4 triple-sec">
                        <h5 class="text-dark">special services</h5>
                        <ul class="list-group mt-3">
                            <li class="list-group-item border-0">
                                <i class="fas fa-heartbeat mr-3"></i>Cras justo odio</li>
                            <li class="list-group-item border-0">
                                <i class="fas fa-user-md mr-3"></i>Dapibus ac facilisis in</li>
                            <li class="list-group-item border-0">
                                <i class="fas fa-pills mr-3"></i>Morbi leo risus</li>
                            <li class="list-group-item border-0">
                                <i class="fas fa-thermometer mr-3"></i>
                            Porta ac consectetur ac</li>
                            <li class="list-group-item border-0">
                                <i class="fas fa-ambulance mr-3"></i>Vestibulum at eros</li>
                        </ul>
                    </div>
                    <div class="col-lg-4  triple-sec">
                        <h5>opening hours</h5>
                        <ul class="list-unstyled">
                            <li class="clearfix py-3">
                                <span class="float-left"> Monday - Friday </span>
                                <div class="value float-right"> 9.00 - 20.00 </div>
                            </li>
                            <li class="clearfix border-top border-bottom my-3 py-3">
                                <span class="float-left"> Saturday </span>
                                <div class="value float-right"> 10.00 - 16.00 </div>
                            </li>
                            <li class="clearfix py-3">
                                <span class="float-left"> Sunday </span>
                                <div class="value float-right"> 9.30 - 18.00 </div>
                            </li>
                        </ul>
                    </div>
                    <div class="col-lg-4 triple-sec">
                        <h5 class="text-black">critical care expertise</h5>
                        <p class="pt-4">ulla quis lorem ut libero malesuada feugiat. Curabitur aliquet quam id dui posuere blandit. Mauris blandit
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
