import React from 'react';
import { Row, Button, Form, Table } from 'react-bootstrap';



function Symptoms(props) {
    let i = 0;
    let diagnoses = props.treatmentHistory ?
        props.treatmentHistory.diagnoses ?
            props.treatmentHistory.diagnoses
            : [] : [];


    function getDate(number) {
        let date = new Date(number);
        return date.toDateString();
    }
  
    return (
        <div>
            <Row className='justify-content-center'>
                <Form className='w-50'>
                    <Form.Group className='justify-content-center'>
                        <Form.Label>Describe your symptom, that worries you at the moment?</Form.Label>
                        <Form.Control as="textarea" rows={6} />
                    </Form.Group>
                    <Form.Group>
                        <Button type='reset' className='w-50' variant="outline-secondary">Clear</Button>
                        <Button className='w-50' variant="outline-primary">Send to doctor</Button>
                    </Form.Group>
                </Form>
            </Row>
            <Row>
                <div className='bg-secondary w-100 pl-5  pb-3 pt-3'>
                    <h2 >The Symptoms</h2>
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
                                        <td>{symptom.name}</td>
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