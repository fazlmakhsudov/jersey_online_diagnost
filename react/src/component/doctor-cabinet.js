import React from 'react';
import InnerBanner from './common/inner-banner.js';
import BreadCrump from './common/bread-crump.js';

import Footer from './common/footer.js';
import { Row, Col, Tab, Tabs, Form, Button } from 'react-bootstrap';

export default function DoctorCabinet(props) {
    return (
        <div >
            <InnerBanner menu='My cabinet' />
            <BreadCrump menu='My cabinet' />
            <div className='mb-3 '>
                <Tabs className='justify-content-center' defaultActiveKey="profile" id="uncontrolled-tab-example">
                    <Tab eventKey="profile" title="Profile" className='container mt-5'>
                        <Form>
                            <Form.Group as={Row} className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Name
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control type='text' name='name' placeholder='Type your name' />
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Surname
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control type='text' name='surname' placeholder='Type your surname' />
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Birthdate
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control type='date' name='birthdate' placeholder='Type your birthdate' />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Email
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control type='email' placeholder='Type your email' />
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row}  className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Phone
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control type='text' placeholder='Type your phone' />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Gender
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control name='gender' as="select" >
                                        <option>Male</option>
                                        <option>Female</option>

                                    </Form.Control>
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} controlId="location" className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Location
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control type='text' placeholder='Type your location' />
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} controlId="formPlaintextPassword" className='justify-content-center'>
                                <Form.Label column sm="2">
                                    Password
                                </Form.Label>
                                <Col sm="4">
                                    <Form.Control type="password" placeholder="Type your password" />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} controlId="formPlaintextPassword" className='justify-content-center'>
                                <Button variant="secondary" type="reset" className='w-25 mr-2'>
                                    Reset
                               </Button>
                                <Button variant="primary" type="submit" className='w-25'>
                                    Edit
                               </Button>
                            </Form.Group>
                        </Form>
                    </Tab>

                    <Tab eventKey="requests" title="Requests">
                        Requests
                    </Tab>
      
                    <Tab eventKey="assignments" title="Assignments">
                        Assignments
                    </Tab>
                
                
                </Tabs>
            </div>
            <div className='justify-content-center text-center'>
                <Row>
                    <Col sm={4}>

                    </Col>
                    <Col sm={8}>
                        <div>
                            <div className="spinner-wrapper">
                                <div className="donut"></div>
                            </div>
                        </div>
                    </Col>

                </Row>
            </div>





            <Footer />
        </div >
    );
}
