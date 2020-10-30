import React from 'react';
import { Row, Col, Table } from 'react-bootstrap';



export default function TreatmentHistory(props) {

    let treatmentHistory = props.treatmentHistory;

    function getDate(number) {
        let date = new Date(number);
        return date.toDateString();
    }

    return (
        <div>
            <div className='bg-secondary w-100 mb-5'>
                <h2 className='pt-4 pb-2 text-center'>The Treatment History</h2>
                <Row>
                    <Col sm={2}></Col>
                    <Col sm={3}>
                        <h4 className=''>Diagnoses' number :</h4>
                    </Col>
                    <Col sm={6}>
                        <h4>{treatmentHistory && treatmentHistory.diagnoses.length > 1 ? treatmentHistory.diagnoses.length : '-'}</h4>
                    </Col>
                </Row>

                <Row>
                    <Col sm={2}></Col>
                    <Col sm={3}>
                        <h4 className=''>Created date :</h4>
                    </Col>
                    <Col sm={6}>
                        <h4>{treatmentHistory ? getDate(treatmentHistory.createdDate) : ''}</h4>
                    </Col>
                </Row>
                
                <Row className='pb-3'>
                    <Col sm={2}></Col>
                    <Col sm={3}>
                        <h4 className=''>Updated date date : </h4>
                    </Col>
                    <Col sm={6}>
                        <h4>{treatmentHistory ? getDate(treatmentHistory.updatedDate) : ''}</h4>
                    </Col>
                </Row>
            </div>
            <div>
                <Table responsive="md">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Table heading</th>
                            <th>Table heading</th>
                            <th>Table heading</th>
                            <th>Table heading</th>
                            <th>Table heading</th>
                            <th>Table heading</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                            <td>Table cell</td>
                        </tr>
                    </tbody>
                </Table>
            </div>




        </div>
    );
}
