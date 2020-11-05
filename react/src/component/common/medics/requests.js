import React, { useState } from 'react';
import { Row, Table, Button, Modal, Form, FormGroup, FormControl } from 'react-bootstrap';
import axios from 'axios';

export default function Requests(props) {
    let i = 0;
    let diseasesMap = props.diseasesMap;
    let patients = props.patients;
    const [show, setShow] = useState(false);
    const [name, setName] = useState('');
    const [diagnosesId, setDiagnosesId] = useState(-1);
    const [patientDiagnoses, setPatientDiagnoses] = useState([]);
    const [showCard, setShowCard] = useState(false);
    const [patient, setPatient] = useState({});
    const [response, setResponse] = useState('');
    const [diseasesId, setDiseasesId] = useState(-1);
    const [showUpdateSymptom, setShowUpdateSymptom] = useState(false);
    const [id, setId] = useState(-1);


    function getDate(number) {
        let date = new Date(number);

        return date.toDateString();
    }


    function calculateAge(number) {
        var date1 = new Date(number);
        var date2 = new Date();

        var diff = new Date(date2.getTime() - date1.getTime());

        let year = diff.getUTCFullYear() - 1970;
        let months = diff.getUTCMonth();
        year = year ? year + ' year(s) ' : '';
        months = months ? months + ' month(s)' : '-';
        return year + '' + months;
    }

    function handleUpdateSymptom(patient, symptom) {
        let treatmentHistory = patient.treatmentHistory;
        let diagnosesMap = [];
        if (treatmentHistory && treatmentHistory.diagnoses) {
            treatmentHistory.diagnoses.map(diagnos =>
                diagnosesMap.push({
                    id: diagnos.id,
                    name: diagnos.name
                })
            )
        }
        setPatient(patient);
        setPatientDiagnoses(diagnosesMap);
        setId(symptom.id);
        setName(symptom.name);
        setDiagnosesId(symptom.diagnosesId);
        setDiseasesId(symptom.diseasesId);
        setShowUpdateSymptom(true);
    }

    function handlePatientCard(patient) {
        let treatmentHistory = patient.treatmentHistory;
        let diagnosesMap = [];
        if (treatmentHistory && treatmentHistory.diagnoses) {
            treatmentHistory.diagnoses.map(diagnos =>
                diagnosesMap.push({
                    id: diagnos.id,
                    name: diagnos.name
                })
            )
        }
        setPatient(patient);
        setDiseasesId(patient.diseasesId);
        setPatientDiagnoses(diagnosesMap);
        setResponse('');
        setName('');
        setShowCard(true);
    }


    function addDiagnos() {
        let diagnosToSend = {
            id: -1,
            name: name,
            treatmentHistoriesId: patient.treatmentHistory.id,
        };

        axios({
            'method': 'POST',
            'url': "http://localhost:8080/online-diagnost/diagnoses",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },
            data: diagnosToSend,

        }).then(response => {

            if (response.status === 200) {

                setResponse('Diagnos has been added. Reopen window to see new list of diagnoses.');
                setName('');
                setPatient({});
                setPatientDiagnoses([]);
                setDiseasesId(-1);
                props.setFlag(true);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function handleMakeAssignment(patient, diagnosId = -1) {
        let treatmentHistory = patient.treatmentHistory;
        let diagnosesMap = [];
        if (treatmentHistory && treatmentHistory.diagnoses) {
            treatmentHistory.diagnoses.map(diagnos =>
                diagnosesMap.push({
                    id: diagnos.id,
                    name: diagnos.name
                })
            )
        }
        setPatient(patient);
        setPatientDiagnoses(diagnosesMap);
        setDiagnosesId(diagnosId);
        setShow(true);
    }

    function addAssignment() {
        let assignmentToSend = {
            id: -1,
            name: name,
            diagnosesId: diagnosesId,
            medicsId: props.medicsId
        };

        axios({
            'method': 'POST',
            'url': "http://localhost:8080/online-diagnost/assignments",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },
            data: assignmentToSend,

        }).then(response => {
            if (response.status === 200) {
                setShow(false);
                setName('');

                setDiagnosesId(-1);
                props.setFlag(true);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function updateSymptom() {
        let symptomToSend = {
            id: id,
            name: name,
            diagnosesId: diagnosesId,
            diseasesId: diseasesId
        };



        axios({
            'method': 'PUT',
            'url': "http://localhost:8080/online-diagnost/symptoms",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },
            data: symptomToSend,

        }).then(response => {
            if (response.status === 200) {
                setShowUpdateSymptom(false);
                setName('');
                setPatient({});
                setPatientDiagnoses([]);
                setId(-1);
                setDiseasesId(-1);
                setDiagnosesId(-1);
                props.setFlag(true);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function handleDiseasesIdChangle(diseasesId) {
        let patientToSend = {
            id: patient.id,
            condition: patient.condition,
            diseasesId: diseasesId
        };
        axios({
            'method': 'PUT',
            'url': "http://localhost:8080/online-diagnost/patients",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },
            data: patientToSend,

        }).then(response => {
            if (response.status === 200) {
                console.log('updated patient');
                setDiseasesId(diseasesId);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    return (
        <div>
            <Row className='m-5'>
                <div className='bg-secondary w-100 pl-5 pb-3 pt-3'>
                    <div className='row'>
                        <div className='col-6'>
                            <h2>The Requests</h2>
                        </div>
                        <div className='col-6 text-right'>
                            <Button variant='outline-light' className='w-25 mr-5' size='lg' onClick={() => props.setFlag(true)}><span className='text-primary h5'>Refresh</span></Button>
                        </div>
                    </div>
                </div>

                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Patient id</th>
                            <th>Name / Surname</th>
                            <th>Age</th>
                            <th>Diagnos</th>
                            <th>Diasease</th>
                            <th>Symptoms</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Patient id</th>
                            <th>Name / Surname</th>
                            <th>Age</th>
                            <th>Diagnos</th>
                            <th>Diasease</th>
                            <th>Symptoms</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Actions</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        {
                            patients.map((patient, index) =>
                                patient.treatmentHistory.diagnoses.filter(diagnos => {

                                    if (diagnos.name === 'no_diagnos') {

                                        return diagnos;
                                    }
                                })
                                    .map(diagnos =>
                                        diagnos.symptoms.map((symptom, index2) =>
                                            <tr key={index * 2 + index2 * 3}>

                                                <td>{++i}</td>
                                                <td>{patient.id}</td>
                                                <td>{patient.user.name + ' ' + patient.user.surname}</td>
                                                <td>{calculateAge(patient.user.birthdate)}</td>

                                                <td>{diagnos.name}</td>
                                                <td>{diseasesMap[symptom.diseasesId] ? diseasesMap[symptom.diseasesId].name : symptom.diseasesid}</td>

                                                <td style={{ whiteSpace: 'pre-wrap', textOverflow: 'ellipsis', maxWidth: '350px' }}>{symptom.name}</td>
                                                <td>{getDate(symptom.createdDate)}</td>
                                                <td>{getDate(symptom.updatedDate)}</td>
                                                <td>
                                                    <Button block variant='outline-primary' onClick={() => handlePatientCard(patient)}>Patient card</Button>
                                                    <Button block variant='outline-primary' onClick={() => handleMakeAssignment(patient, diagnos.id)}>Make assignment</Button>

                                                    <Button block variant='outline-primary' onClick={() => handleUpdateSymptom(patient, symptom)}>Update</Button>
                                                </td>
                                            </tr>
                                        )
                                    )
                            )
                        }
                    </tbody>
                </Table>
            </Row>

            <Modal show={show} onHide={() => setShow(false)} animation={true}>
                <Modal.Header closeButton>
                    <Modal.Title className="display-4">Add form</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Group className='justify-content-center'>
                        <Form.Label>Assignment's description</Form.Label>
                        <Form.Control as="textarea" rows={6} name='name' value={name} onChange={(e) => setName(e.target.value)}
                            placeholder="Type assignment as much detailed in order to be understood distinctly"
                            minLength='15' />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Diagnos</Form.Label>
                        <Form.Control as="select" value={diagnosesId} onChange={(e) => setDiagnosesId(e.target.value)}>

                            {
                                patientDiagnoses.map((diagnos, index) =>
                                    <option key={index} value={diagnos.id}>{diagnos.name}</option>
                                )
                            }

                        </Form.Control>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShow(false)}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => addAssignment()} disabled={!(name.length > 8)}>
                        Add
                    </Button>
                </Modal.Footer>
            </Modal>

            <Modal show={showUpdateSymptom} onHide={() => setShowUpdateSymptom(false)} animation={true}>
                <Modal.Header closeButton>
                    <Modal.Title className="display-4">Update form</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Group className='justify-content-center'>
                        <Form.Label>Symptom's description</Form.Label>
                        <Form.Control as="textarea" rows={6} name='name' value={name}
                            minLength='15' readOnly />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Diagnos</Form.Label>
                        <Form.Control as="select" value={diagnosesId} onChange={(e) => setDiagnosesId(e.target.value)}>

                            {
                                patientDiagnoses.map((diagnos, index) =>
                                    <option key={index} value={diagnos.id}>{diagnos.name}</option>
                                )
                            }

                        </Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Disease</Form.Label>
                        <Form.Control as="select" value={diseasesId} onChange={(e) => setDiseasesId(e.target.value)}>

                            {
                                Object.values(diseasesMap).map((disease, index) =>
                                    <option key={index} value={disease.id}>{disease.name}</option>
                                )
                            }

                        </Form.Control>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowUpdateSymptom(false)}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => updateSymptom()} >
                        Save
                    </Button>
                </Modal.Footer>
            </Modal>

            <Modal show={showCard} onHide={() => setShowCard(false)} animation={true}>
                <Modal.Header closeButton>
                    <Modal.Title className="display-4">Patient card</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className='row'>
                        <span className='col-5 pl-3'>Name:  </span>
                        <span className='col-7'>{patient.user ? patient.user.name + ' ' + patient.user.surname : ''}</span>
                    </div>
                    <div className='row'>
                        <span className='col-5 pl-3' >Treatment history id:  </span>
                        <span className='col-7'> {patient.treatmentHistory ? patient.treatmentHistory.id : ''}</span>
                    </div>
                    <div className='row'>
                        <span className='col-5 pl-3' >Disease:  </span>

                        <Form.Group className='col-7'>
                            <Form.Control as="select" value={diseasesId} onChange={(e) => handleDiseasesIdChangle(e.target.value)}>

                                {
                                    Object.values(diseasesMap).map((disease, index) =>
                                        <option key={index} value={disease.id}>{disease.name}</option>
                                    )
                                }

                            </Form.Control>
                        </Form.Group>

                    </div>

                    <br />
                    <div style={{ maxHeight: '300px', overflow: 'auto' }}>
                        <h6 className='pl-1'><u>Diagnoses:</u></h6>
                        <div className='row mb-2'>

                            <span className='col-2 pl-3' ><em>Id</em></span>
                            <span className='col-10'><em>Desription</em></span>
                        </div>
                        {

                            patientDiagnoses.map((diagnos, index) =>
                                <div className='row' key={index + diagnos.id}>
                                    <span className='col-2 pl-3' >{diagnos.id}</span>
                                    <span className='col-10'>{diagnos.name}</span>
                                </div>

                            )
                        }

                    </div>
                    <hr className='bg-secondary' style={{ height: '15px' }} />
                    <FormGroup>
                        <Form.Label>New Diagnos</Form.Label>
                        <FormControl
                            type="text"
                            autoFocus
                            value={name}
                            onChange={e => setName(e.target.value)}
                            placeholder='Type diagnos description' minLength='6' />
                        <div className='text-right mt-2'>
                            <Button className='w-50' onClick={() => addDiagnos()}>Add new diagnos</Button>
                        </div>
                        <p style={{ color: "green" }}>{response}</p>

                    </FormGroup>
                </Modal.Body>
                <Modal.Footer>
                    <Button className='w-50' variant="secondary" onClick={() => setShowCard(false)}>
                        Close
                    </Button>

                </Modal.Footer>
            </Modal>
        </div>
    );
}
