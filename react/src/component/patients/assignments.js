import React from 'react';
import { Row, Table, Button } from 'react-bootstrap';


export default function Assignments(props) {
    let i = 0;
    let diagnoses = props.diagnoses;

    function getDate(number) {
        let date = new Date(number);
        return date.toDateString();
    }
  
    return (
        <div>
            
            <Row>
                <div className='bg-secondary w-100 pl-5 pb-3 pt-3'>
                    <div className='row'>
                        <div className='col-6'>
                            <h2>The Assignments</h2>
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
                            <th>Medic</th>
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
                            <th>Medic</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Status</th>
                        </tr>
                    </tfoot>


                    <tbody>

                        {
                            diagnoses.map((diagnos) =>
                                diagnos.assignments.map((assignment, index) =>
                                    <tr key={index}>
                                        <td>{i = i + 1}</td>
                                        <td style={{whiteSpace:'pre-wrap', textOverflow:'ellipsis', maxWidth:'200px'}}>{assignment.name}</td>
                                        <td>{diagnos.name}</td>
                                        <td>{assignment.medicsId}</td>
                                        <td>{getDate(assignment.createdDate)}</td>
                                        <td>{getDate(assignment.updatedDate)}</td>
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
