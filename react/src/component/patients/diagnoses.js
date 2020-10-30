import React from 'react';
import {Table } from 'react-bootstrap';


export default function Diagnoses(props) {
    let i = 0;
    let diagnoses = props.diagnoses;

    function getDate(number) {
        let date = new Date(number);
        return date.toLocaleDateString();
    }

    return (
        <div>
            <div className='bg-secondary w-100'>
                <h2 className='pt-3 pb-3 pl-5'>The Diagnoses</h2>
            </div>
            <div>
                <Table responsive="md">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Symptoms' number</th>
                            <th>Assignments' number</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th>Symptoms' number</th>
                            <th>Assignments' number</th>
                            <th>Created date</th>
                            <th>Updated date</th>
                            <th>Status</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        {
                            diagnoses.length > 0 ?
                                diagnoses.map((diagnos, index) =>
                                    <tr key ={index}>
                                        <td>{++i}</td>
                                        <td>{diagnos.name}</td>
                                        <td>{diagnos.symptoms.length >= 1 ? diagnos.symptoms.length : '-'}</td>
                                        <td>{diagnos.assignments.length >= 1 ? diagnos.assignments.length : '-'}</td>
                                        <td>{getDate(diagnos.createdDate)}</td>
                                        <td>{getDate(diagnos.updatedDate)}</td>
                                        <td>--</td>
                                    </tr>
                                )
                                : []

                        }

                    </tbody>
                </Table>
            </div>




        </div>
    );
}
