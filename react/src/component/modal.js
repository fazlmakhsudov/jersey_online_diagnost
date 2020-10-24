import React, { useState } from 'react';
import { Form, Modal, Row, Col, Button } from 'react-bootstrap';
import axios from 'axios';

function ModalWindow(props) {
    let url = props.url + "/user";
    let showFlag = props.showFlag;
    let setShowFlag = props.setShowFlag;
    let setAuthorityFlag = props.setAuthorityFlag;
    let setAuthorization = props.setAuthorization;

    const [name, setName] = useState('');
    const [password, setPassword] = useState('');

    function checkCredential() {
        axios({
            'method': 'POST',
            'url': url + "/signin",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': "dadsdsfsdf",
            },
            data: {
                name: name,
                password: password,
            }
        }).then(response => {
            let token = response.headers['x-auth-token'];
            console.log("token:", token);
            console.log('resp ', response);
           
            if (response.status === 200) {
                setAuthorityFlag(true);
                setAuthorization(response.data);
            }
            clearFields();
        }).catch(error => {
            alert('It has appeared \n' + error);
            clearFields();
        });
    }

    function handleSubmit() {
        setShowFlag(false);
        checkCredential();
    }

    function clearFields() {
        setName('');
        setPassword('');
    }

    return (
        <Modal show={showFlag} onHide={() => setShowFlag(false)}>
            <Modal.Header closeButton>
                <Modal.Title>Sign in</Modal.Title>
            </Modal.Header>
            <Modal.Body as={Row} className='text-center'>
                <Form as={Col} className='w-50'>
                    <Form.Group>
                        <Form.Control type="text" value={name} placeholder="Type login" onChange={(e) => setName(e.target.value)} />
                    </Form.Group>
                </Form>
                <Form as={Col} className='w-50'>
                    <Form.Group>
                        <Form.Control type="password" value={password} placeholder="Type password" onChange={(e) => setPassword(e.target.value)} />
                    </Form.Group>
                </Form>
                <Col sm={12}>
                    <Button type='submit' onClick={() => handleSubmit()} block>Click me</Button>
                </Col>
            </Modal.Body>
        </Modal>
    );
}

export default ModalWindow;