import React, { useState, useEffect } from 'react';
import { Form, Button, Table } from 'react-bootstrap';
import axios from 'axios';


export default function ContactForm(props) {
    const [flag, setFlag] = useState(true);
    const [questions, setQuestions] = useState([]);
    const [answer, setAnswer] = useState([]);
    const [showQuestionFlag, setShowQuestionFlag] = useState(true);
    const [validateFlag, setValidateFlag] = useState(false);
    const [response, setResponse] = useState('');

    async function handleSubmit() {
        let questionsToSend = formQuestionsRequest();


        axios({
            'method': 'POST',
            'url': "http://localhost:8080/online-diagnost/diagnoster",
            'headers': {
                'Content-Type': 'application/json',
            },
            data: {
                email: sessionStorage.getItem('email') ? sessionStorage.getItem('email') : 'unauthorised',
                questions: questionsToSend
            },

        }).then(response => {

            if (response.status === 200) {
                console.log(response.data);
                setAnswer(response.data);
                setShowQuestionFlag(false);
            } else {
                setResponse('Unfortunately, the diagnosis is certain. Try to highlight only significant symptoms.');
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }


    function getQuestionary() {
        axios({
            'method': 'GET',
            'url': 'http://localhost:8080/online-diagnost/questionaries',
            'headers': {
                'Content-Type': 'application/json',
            },


        }).then(response => {
            if (response.status === 200) {
                setQuestions(response.data.questions);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function formQuestionsRequest() {
        let questionsToSend = [];
        questions.map(question => {
            let questionRequest = {
                id: question.id,
                name: question.name,
                answer: question.answer,
                questionariesId: question.questionariesId
            }
            questionsToSend.push(questionRequest);
        });
        console.log(questionsToSend);
        return questionsToSend;
    }

    function formRequestHtml() {
        return <div className="contact-top1">
            <h5 className="sub-title-wthree text-center">request form</h5>
            <Form className="pc-contact">
                {
                    questions.map((question, index) =>
                        <Form.Check className="ml-5 mt-4" key={index} custom type='checkbox' id={`checkbox` + index} label={question.name} onClick={() => {
                            questions[index].answer === 'true' ? questions[index].answer = 'false' : questions[index].answer = 'true';

                            setQuestions(questions);
                            setValidateFlag(validate());
                            setResponse('');
                        }} autoFocus />
                    )
                }
                <div className="text-left mt-4">
                    <Button variant='outline-secondary' className='ml-5' type='reset' onClick={() => {
                        questions.map(question => question.answer = 'false');
                        setQuestions(questions);
                    }} style={{ minWidth: '25%' }}>Clear</Button>
                    <Button variant='outline-primary' className='ml-2' onClick={() => handleSubmit()} style={{ minWidth: '25%' }}
                        disabled={!validateFlag}>Send</Button>
                </div>
            </Form>
            <div className='mt-3'>
                <h6 className='text-danger'>{response}</h6>
            </div>
        </div>
    }

    function validate() {
        let isTrueNumber = 0;
        questions.map(question => {
            if (question.answer === "true") {
                isTrueNumber++;
            }
        });
        return isTrueNumber >= 2;
    }

    function formResponseHtml() {
        let i = 0;
        return <div className="contact-top1">
            <h5 className="sub-title-wthree text-center">Prediction</h5>
            <div>
                <Table responsive="md">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Diagnos</th>
                            <th>Probability</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            answer.map((diagnos, index) =>
                                <tr key={index}>
                                    <td>{++i}</td>
                                    <td>{diagnos.diseaseName}</td>
                                    <td>{Number(diagnos.probability * 100).toFixed(2) + ' percent'}</td>
                                </tr>
                            )
                        }


                    </tbody>
                </Table>
            </div>
            <div className="text-left mt-4">

                <Button variant='outline-primary' className='ml-2' onClick={() => {
                    questions.map(question => question.answer = 'false');
                    setQuestions(questions);
                    setShowQuestionFlag(true);
                    setValidateFlag(false);
                }} style={{ minWidth: '25%' }}>Make another request</Button>
            </div>
        </div>
    }

    useEffect(() => {
        if (flag) {
            getQuestionary();
            setFlag(false);
        }
    });

    return (
        <section className="wthree-row pt-3 pb-lg-5 w3-contact">
            <div className="container py-sm-5 pt-0 pb-5">
                <div className="title-section text-center pb-lg-5">
                    <h4>24/7</h4>
                    <h3 className="w3ls-title text-center text-capitalize">affordable medicien</h3>
                </div>
                <div className="row contact-form pt-lg-5">
                    {/* <!-- contact details --> */}
                    <div className="col-lg-4 contact-bottom mt-lg-0 mt-5">
                        <div className="contact-details-top">
                            <h5 className="sub-title-wthree">contact us</h5>
                            <img src="images/contact.jpg" className="img-fluid  mb-3" alt='' />
                            <p>Doc. Anna, therapist, responds in few minutes.</p>
                        </div>
                        <div className="address">
                            <h5 className="sub-title-wthree">contact info</h5>
                            <div className="row wthree-cicon">
                                <span className="fas fa-envelope-open mr-3"></span>
                                <a href="mailto:info@example.com">mail@online-diagnost.com</a>
                            </div>
                            <div className="row wthree-cicon">
                                <span className="fas fa-phone-volume mr-3"></span>
                                <h6>+380 666 66 66</h6>
                            </div>
                            <div className="row wthree-cicon">
                                <span className="fas fa-globe mr-3"></span>
                                <a href="/home">www.online-diagnost.com</a>
                            </div>
                        </div>
                        <div className="footerv2-w3ls">
                            <h5 className="sub-title-wthree">follow us</h5>
                            <ul className="social-iconsv2 agileinfo">
                                <li>
                                    <a href="http://facebook.com">
                                        <i className="fab fa-facebook-f"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="https://twitter.com">
                                        <i className="fab fa-twitter"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="https://www.google.com">
                                        <i className="fab fa-google-plus-g"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="https://linkedin.com">
                                        <i className="fab fa-linkedin-in"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    {/* <!-- //contact details --> */}
                    <div className="col-lg-8 wthree-form-left px-lg-5 mt-lg-0 mt-5">
                        {/* <!-- contact form grid --> */}

                        {
                            showQuestionFlag ? formRequestHtml() : formResponseHtml()
                        }

                        {/* <!--  //contact form grid ends here --> */}
                    </div>
                </div>
                {/* <!-- //contact details container --> */}
            </div>
            {/* <!-- contact map grid --> */}
            <div className="map contact-right p-sm-5 p-3 pb-lg-5">
                <div className="title-section text-center pb-5">
                    <h4>world of medicine</h4>
                    <h3 className="w3ls-title text-center text-capitalize">Get directions</h3>
                </div>
                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d3955.098115627792!2d36.247567308463495!3d50.000786248652176!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x4847f00cec333b6!2zTlRVICJIUEki!5e0!3m2!1sen!2sua!4v1598288582945!5m2!1sen!2sua"
                    width="600" height="450" frameBorder="0" allowFullScreen="" aria-hidden="false" tabIndex="0" title='Our office'>

                </iframe>
                {/* <iframe src=""
                    allowfullscreen></iframe> */}
            </div>
            {/* <!--//contact map grid ends here--> */}
        </section>
    );
}
