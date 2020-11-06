import React, { useState } from 'react';
import { Row, Button, Form, Table } from 'react-bootstrap';
import axios from 'axios';


function Symptoms(props) {
    const [name, setName] = useState('');
    let parentSetFlag = props.setFlag;


    let i = 0;
    let diagnoses = props.treatmentHistory ?
        props.treatmentHistory.diagnoses ?
            props.treatmentHistory.diagnoses
            : [] : [];


    function getDate(number) {
        let date = new Date(number);
        return date.toDateString();
    }

    function handleClick() {
        let diagnosId = parseInt(sessionStorage.getItem('diagnosId'));
        let symptom = {
            id: -1,
            name: name,
            diagnosesId: diagnosId,
            diseasesId: -1,
        };
        console.log('fetching symptom', symptom);
        axios({
            'method': 'POST',
            'url': "http://localhost:8080/online-diagnost/symptoms/patient",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },
            data: symptom,

        }).then(response => {
            console.log('resp', response);
            if (response.status === 200) {
                parentSetFlag(true);
                setName('');
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function validateForm() {
        let flag = true;

        flag = name && name.length > 12 ? flag : false;

        return flag;
    }


    return (
        <div>
            <Row className='justify-content-center'>
                <Form>
                    <Form.Group className='justify-content-center'>
                        <Form.Label>Describe your symptom, that worries you at the moment?</Form.Label>
                        <Form.Control as="textarea" rows={6} name='name' value={name} onChange={(e) => setName(e.target.value)}
                            placeholder="Type symptoms as much detailed in order to be understood distinctly"
                            minLength='15' />
                    </Form.Group>
                    <Form.Group>
                        <Button className='w-50' variant="outline-secondary" onClick={() => setName('')}>Clear</Button>
                        <Button className='w-50' variant="outline-primary" onClick={() => handleClick()} disabled={!validateForm()}>Send to doctor</Button>
                    </Form.Group>
                </Form>
            </Row>
            <Row>
                <div className='bg-secondary w-100 pl-5 pb-3 pt-3'>
                    <div className='row'>
                        <div className='col-6'>
                            <h2>The Symptoms</h2>
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
                            <th>Desription</th>
                            <th>Diagnos</th>
                            <th>Disease</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Desription</th>
                            <th>Diagnos</th>
                            <th>Disease</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Status</th>
                        </tr>
                    </tfoot>


                    <tbody>

                        {
                            diagnoses.map((diagnos) =>
                                diagnos.symptoms.map((symptom, index) =>
                                    <tr key={index}>
                                        <td>{i = i + 1}</td>
                                        <td style={{ whiteSpace: 'pre-wrap', textOverflow: 'ellipsis', maxWidth: '200px' }}>{symptom.name}</td>
                                        <td>{diagnos.name}</td>
                                        <td>{symptom.diseasesId}</td>
                                        <td>{getDate(symptom.createdDate)}</td>
                                        <td>{getDate(symptom.updatedDate)}</td>
                                        <td>status --- </td>
                                    </tr>
                                )
                            )
                        }
                    </tbody>
                </Table>
            </Row>


        </div>
    );
}

export default Symptoms;