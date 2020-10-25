import React from 'react';

export default function AgileitServices(props) {
    return (
        <div class="agileits-services py-lg-5 pb-5" id="explore">
        <div class="container py-lg-5">
            <div class="title-section text-center pb-5 border-bottom">
                <h4>world of medicine</h4>
                <h3 class="w3ls-title text-center text-capitalize">centres of excellence</h3>
            </div>
            <div class="agileits-services-row row pt-5">
                <div class="col-lg-3 col-sm-6 agileits-services-grids py-lg-5 py-sm-4 pb-4">
                    <span class="fas fa-user-md"></span>
                    <h4 class="my-3">Gastroenterology</h4>
                </div>
                <div class="col-lg-3 col-sm-6 agileits-services-grids  py-lg-5 py-4">
                    <span class="fas fa-thermometer"></span>
                    <h4 class="my-3">critical care</h4>
                </div>
                <div class="col-lg-3 col-sm-6 agileits-services-grids  py-lg-5 py-4">
                    <span class="far fa-hospital"></span>
                    <h4 class="my-3">Orthopaedics</h4>
                </div>
                <div class="col-lg-3 col-sm-6 agileits-services-grids   py-lg-5 py-4">
                    <span class="fas fa-heartbeat"></span>
                    <h4 class="my-3">Transplants</h4>
                </div>
            </div>
            <div class="row pb-md-5">
                <div class="col-lg-3 col-sm-6 agile_service_bottom agileits-services-grids  py-lg-5 py-4">
                    <span class="fas fa-pills"></span>
                    <h4 class="my-3">preventive medicine</h4>
                </div>
                <div class="col-lg-3 col-sm-6 agile_service_bottom agileits-services-grids  py-lg-5 py-4">
                    <span class="fas fa-ambulance"></span>
                    <h4 class="my-3">emergency care</h4>
                </div>
                <div class="col-lg-3 col-sm-6 agile_service_bottom agileits-services-grids   py-lg-5 py-4">
                    <span class="fas fa-hospital-symbol"></span>
                    <h4 class="my-3">oncology</h4>
                </div>
                <div class="col-lg-3 col-sm-6 agile_service_bottom agileits-services-grids   py-lg-5 py-sm-4 pt-4">
                    <span class="fab fa-medrt"></span>
                    <h4 class="my-3">cardiology</h4>
                </div>
            </div>
        </div>
    </div>
    );
}