import React, { useState, useEffect } from 'react';
import InnerBanner from './common/inner-banner.js';
import BreadCrump from './common/bread-crump.js';

import Footer from './common/footer.js';
import { Tab, Tabs } from 'react-bootstrap';



import Profile from './common/profile.js';
import Requests from './common/medics/requests.js';
import Assignments from './common/medics/assignments.js';

import axios from 'axios';


export default function DoctorCabinet(props) {
    const [flag, setFlag] = useState(true);
    const [patients, setPatients] = useState([]);
    const [diagnosesMap, setDiagnosesMap] = useState([]);
    const [assignmentsMap, setAssignmentsMap] = useState([]);
    const [medicsId, setMedicsId] = useState(-1);
    const [diseasesMap, setDiseasesMap] = useState([]);

    function formDiagnosesMap(patients) {
        let maptempDiagnoses = [];
        let maptempAssignments = [];
        patients.filter(patient => {
            if (patient.treatmentHistory != null) {
                return patient;
            }
        })
            .map((patient) => {

                patient.treatmentHistory.diagnoses.map(diagnos => {
                    let keyTreamentHistory = patient.treatmentHistory.id + "treatmentHistory";

                    if (!maptempDiagnoses[keyTreamentHistory]) {
                        maptempDiagnoses[keyTreamentHistory] = [];
                    }
                    maptempDiagnoses[keyTreamentHistory].push({
                        'id': diagnos.id,
                        'name': diagnos.name
                    });

                    diagnos.assignments.map(assignment => {
                        maptempAssignments[assignment.id + 'assignment'] = {
                            'diagnosName': diagnos.name,
                            'treatmentHistoriesId': patient.treatmentHistory.id,
                            'patientsId': patient.id,
                            'patientsCondition': patient.condition,
                            'patientDiseasesId': patient.diseasesId,
                            'user': patient.user.name + ' ' + patient.user.surname,
                        };
                    });
                })
            });
        setDiagnosesMap(maptempDiagnoses);
        setAssignmentsMap(maptempAssignments);
    }

    function getPatients() {
        axios({
            'method': 'GET',
            'url': "http://localhost:8080/online-diagnost/patients",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },

        }).then(response => {

            if (response.status === 200) {
                setPatients(response.data);
                formDiagnosesMap(response.data);
            }

        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function getDiseases() {
        axios({
            'method': 'GET',
            'url': "http://localhost:8080/online-diagnost/diseases",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },

        }).then(response => {

            if (response.status === 200) {
                setDiseasesMap(response.data);
            }

        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }


    useEffect(() => {
        if (flag) {
            getPatients();
            getDiseases();
            setFlag(false);
        }
    });
    return (
        <div >
            <InnerBanner menu='My cabinet' />
            <BreadCrump menu='My cabinet' />
            <div className='mb-3 '>
                <Tabs className='justify-content-center' defaultActiveKey="requests">
                    <Tab eventKey="profile" title="Profile" className='container pt-5'>
                        <Profile setFlag={setFlag} setMedicsId={setMedicsId} medicFlag={true} />
                    </Tab>

                    <Tab eventKey="requests" title="Requests">
                        <Requests patients={patients} medicsId={medicsId} setFlag={setFlag}
                            diseasesMap={diseasesMap} />
                    </Tab>

                    <Tab eventKey="assignments" title="Assignments">
                        <Assignments diagnosesMap={diagnosesMap} assignmentsMap={assignmentsMap}
                            setFlag={setFlag} flag={flag} diseasesMap={diseasesMap} />
                    </Tab>
                </Tabs>
            </div>
            <Footer />
        </div >
    );
}
