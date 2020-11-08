import React, { useState, useEffect } from 'react';
import { Row, Table, Button } from 'react-bootstrap';
import axios from 'axios';


export default function DiagnosterHistory(props) {
    let i = 0;
    const [flag, setFlag] = useState(true);
    const [answer, setAnswer] = useState([]);

    function getDiagnosterHistory() {

        axios({
            'method': 'GET',
            'url': "http://localhost:8080/online-diagnost/diagnoster",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },

        }).then(response => {
            console.log('resp diagnoster history', response);
            if (response.status === 200) {
                setAnswer(response.data);
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
            getDiagnosterHistory();
            setFlag(false);
        }
    });



    return (
        <div>

            <Row>
                <div className='bg-secondary w-100 pl-5 pb-3 pt-3'>
                    <div className='row'>
                        <div className='col-6'>
                            <h2>The Diagnosting History</h2>
                        </div>
                        <div className='col-6 text-right'>
                            <Button variant='outline-light' className='w-25 mr-5' size='lg' onClick={() => setFlag(true)}><span className='text-primary h5'>Refresh</span></Button>
                        </div>
                    </div>
                </div>

                <Table striped>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Diagnos</th>
                            <th>Probability</th>
                            <th>Symptoms</th>
                            <th>Created date</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>#</th>
                            <th>Diagnos</th>
                            <th>Probability</th>
                            <th>Symptoms</th>
                            <th>Created date</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        {
                            answer.map((diagnos, index) =>
                                <tr key={index}>
                                    <td>{++i}</td>
                                    <td>{diagnos.diseaseName}</td>
                                    <td>{Number(diagnos.probability * 100).toFixed(2) + ' percent'}</td>
                                    <td style={{ whiteSpace: 'pre-wrap', textOverflow: 'ellipsis', maxWidth: '200px' }}>
                                        {
                                            diagnos.symptoms.split(';').map((symptom,index) => (index + 1) + '. ' + symptom.trim() + '\n')
                                        }
                                    </td>
                                    <td>{getDate(diagnos.createdDate)}</td>
                                </tr>
                            )
                        }


                    </tbody>
                </Table>
            </Row>


        </div>
    );
}
