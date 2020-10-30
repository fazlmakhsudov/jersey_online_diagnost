import React, { useState, useEffect } from 'react';
import InnerBanner from './common/inner-banner.js';
import BreadCrump from './common/bread-crump.js';


import Profile from './patients/profile.js';
import TreatmentHistory from './patients/treatmenthistory.js';
import Diagnoses from './patients/diagnoses.js';
import Symptoms from './patients/symptoms.js';
import Assignments from './patients/assignments.js';


import Footer from './common/footer.js';
import { Row, Col, Tab, Tabs } from 'react-bootstrap';
import axios from 'axios';


export default function PatientCabinet(props) {
    let URL = 'http://localhost:8080/online-diagnost/treatment-history/user';
    const [treatmentHistory, setTreatmentHistory] = useState();
    const [flag, setFlag] = useState(true);
    const [diagnoses, setDiagnoses] = useState([]);

    function getDiagnoses(treatmentHistory) {
        let diagnoses = treatmentHistory ?
            treatmentHistory.diagnoses ?
                treatmentHistory.diagnoses
                : []
            : [];
        return diagnoses;
    }

    function getTreatmentHistory() {
        axios({
            'method': 'GET',
            'url': URL,
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },


        }).then(response => {
            if (response.status === 200) {
                setTreatmentHistory(response.data);
                setDiagnoses(getDiagnoses(response.data));
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }



    useEffect(() => {
        if (flag) {
            getTreatmentHistory();
            setFlag(false);
        }
    });

    return (
        <div >
            <InnerBanner menu='My cabinet' />
            <BreadCrump menu='My cabinet' />

            <div >
                <Tabs className='justify-content-center bg-secondary pt-3 pb-3' defaultActiveKey="diagnoses" id="uncontrolled-tab-example">
                    <Tab eventKey="profile" title="Profile" className='container pt-5'>
                        <Profile setFlag={setFlag} />
                    </Tab>

                    <Tab eventKey="treatmenthistory" title="Treatment history" className='container mt-5'>
                        <TreatmentHistory treatmentHistory={treatmentHistory}/>
                    </Tab>
                    <Tab eventKey="diagnoses" title="Diagnoses" className='container mt-5'>
                        <Diagnoses diagnoses={diagnoses} />
                    </Tab>
                    <Tab eventKey="assignments" title="Assignments" className='container mt-5'>
                        <Assignments diagnoses={diagnoses} />
                    </Tab>
                    <Tab eventKey="symptoms" title="Symptoms" className='container mt-5'>
                        <Symptoms treatmentHistory={treatmentHistory} />
                    </Tab>
                    <Tab eventKey="diagnoster" title="Diagnosting history" className='container mt-5'>
                        Here will be the results of all testing
                    </Tab>
                </Tabs>
            </div>
            <div className='justify-content-center text-center'>
                <Row>
                    <Col sm={4}>

                    </Col>
                    <Col sm={8}>
                        <div>
                            <div className="spinner-wrapper">
                                <div className="donut"></div>
                            </div>
                        </div>
                    </Col>

                </Row>
            </div>





            <Footer />
        </div >
    );
}