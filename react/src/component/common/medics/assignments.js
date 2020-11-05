import React, { useState, useEffect } from 'react';
import { Row, Table, Button, Modal, Form, FormGroup, FormControl } from 'react-bootstrap';
import axios from 'axios';

export default function Assignments(props) {
    let i = 0;

    const [flag, setFlag] = useState(true);
    const [medic, setMedic] = useState();
    const [show, setShow] = useState(false);
    const [name, setName] = useState('');
    const [assignment, setAssignment] = useState({});
    const [patientDiagnoses, setPatientDiagnoses] = useState([]);
    const [diagnosesId, setDiagnosesId] = useState(-1);
    const [showCard, setShowCard] = useState(false);
    const [treatmentHistoriesId, setTreatmentHistoriesId] = useState(-1);
    const [response, setResponse] = useState('');
    const [diseasesId, setDiseasesId] = useState(-1);

    function handleUpdate(assignment) {
        setPatientDiagnoses(props.diagnosesMap[getAssignmentsMapData(assignment.id) + 'treatmentHistory']);
        setName(assignment.name);
        setDiagnosesId(assignment.diagnosesId);
        setAssignment(assignment);
        setShow(true);
        setResponse('');
    }

    function handlePatientCard(assignment) {
        setResponse('');
        setPatientDiagnoses(props.diagnosesMap[getAssignmentsMapData(assignment.id) + 'treatmentHistory']);
        setName('');
        setAssignment(assignment);
        setDiseasesId(getAssignmentsMapData(assignment.id, 'patientDiseasesId'));
        setTreatmentHistoriesId(getAssignmentsMapData(assignment.id));
        setShowCard(true);
    }


    function addDiagnos() {
        let diagnosToSend = {
            id: -1,
            name: name,
            treatmentHistoriesId: treatmentHistoriesId,
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
                setAssignment({});
                setTreatmentHistoriesId(-1);
                setFlag(true);
                props.setFlag(true);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function update() {
        let assignmentToSend = {
            id: assignment.id,
            name: name,
            diagnosesId: diagnosesId,
            medicsId: assignment.medicsId
        };

        axios({
            'method': 'PUT',
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
                setAssignment({});
                setDiagnosesId(-1);
                setFlag(true);
                props.setFlag(true);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }


    function getAssignments() {
        axios({
            'method': 'GET',
            'url': "http://localhost:8080/online-diagnost/medics/single",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },

        }).then(response => {

            if (response.status === 200) {
                setMedic(response.data);
            }

        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function getAssignmentsMapData(assignmentId, key = 'treatmentHistoriesId') {
        let array = props.assignmentsMap;
        if (assignmentId && Object.keys(array).length > 0) {
            // let value = key === 'user' ?
            //     array[assignmentId + 'assignment'].user :
            //     key === 'diagnosName' ?
            //         array[assignmentId + 'assignment'].diagnosName : array[assignmentId + 'assignment'].treatmentHistoriesId
            // return value;
            let value;
            switch (key) {
                case 'user':
                    value = array[assignmentId + 'assignment'].user;
                    break;
                case 'diagnosName':
                    value = array[assignmentId + 'assignment'].diagnosName;
                    break;
                case 'patientsId':
                    value = array[assignmentId + 'assignment'].patientsId;
                    break;
                case 'patientsCondition':
                    value = array[assignmentId + 'assignment'].patientsCondition;
                    break;
                case 'patientDiseasesId':
                    value = array[assignmentId + 'assignment'].patientDiseasesId;
                    break;
                default:
                    value = array[assignmentId + 'assignment'].treatmentHistoriesId;
            }
            return value;
        }
        return assignmentId;
    }

    function handleDiseasesIdChangle(diseasesId) {
        let patientToSend = {
            id: getAssignmentsMapData(assignment.id, 'patientsId'),
            condition: getAssignmentsMapData(assignment.id, 'patientsCondition'),
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




    function getDate(number) {
        let date = new Date(number);
        return date.toDateString();
    }

    useEffect(() => {
        if (flag) {
            getAssignments();
            setFlag(false);
        }
    });

    return (
        <div>

            <Row className='ml-5 mr-5'>
                <div className='bg-secondary w-100 pl-5 pb-3 pt-3'>
                    <div className='row'>
                        <div className='col-6'>
                            <h2>The Assignments</h2>
                        </div>
                        <div className='col-6 text-right'>
                            <Button variant='outline-light' className='w-25 mr-5' size='lg' onClick={() => setFlag(true)}><span className='text-primary h5'>Refresh</span></Button>
                        </div>
                    </div>
                </div>

                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Patient</th>
                            <th>Treatment history id</th>
                            <th>Diagnos</th>
                            <th>Desription</th>

                            <th>Medic id</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Patient</th>
                            <th>Treatment history id</th>
                            <th>Diagnos</th>
                            <th>Desription</th>

                            <th>Medic id</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </tfoot>


                    <tbody>
                        {
                            medic ?
                                medic.assignments.map((assignment, index) =>
                                    <tr key={index}>
                                        <td>{i = i + 1}</td>
                                        <td>{getAssignmentsMapData(assignment.id, 'user')}</td>
                                        <td>{getAssignmentsMapData(assignment.id)}</td>
                                        <td>{getAssignmentsMapData(assignment.id, 'diagnosName')}</td>
                                        <td style={{ whiteSpace: 'pre-wrap', textOverflow: 'ellipsis', maxWidth: '350px' }}>{assignment.name}</td>

                                        <td>{assignment.medicsId}</td>
                                        <td>{getDate(assignment.createdDate)}</td>
                                        <td>{getDate(assignment.updatedDate)}</td>
                                        <td>Status</td>
                                        <td>
                                            <Button block variant='outline-primary' onClick={() => handleUpdate(assignment)}>Update</Button>
                                            <Button block variant='outline-primary' onClick={() => handlePatientCard(assignment)}>Patient card</Button>
                                        </td>
                                    </tr>
                                )
                                : <tr />
                        }
                    </tbody>
                </Table>
            </Row>

            <Modal show={show} onHide={() => setShow(false)} animation={true}>
                <Modal.Header closeButton>
                    <Modal.Title className="display-4">Update form</Modal.Title>
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
                    <Button variant="primary" onClick={() => update()}>
                        Save Changes
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
                        <span className='col-7'>{getAssignmentsMapData(assignment.id, 'user')}</span>
                    </div>
                    <div className='row'>
                        <span className='col-5 pl-3' >Treatment history id:  </span>
                        <span className='col-7'> {getAssignmentsMapData(assignment.id)}</span>
                    </div>
                    <div className='row'>
                        <span className='col-5 pl-3' >Disease:  </span>

                        <Form.Group className='col-7'>
                            <Form.Control as="select" value={diseasesId} onChange={(e) => handleDiseasesIdChangle(e.target.value)}>

                                {
                                    Object.values(props.diseasesMap).map((disease, index) =>
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
