import React, { useState } from "react";
import { Button, FormGroup, FormControl, Form, Col } from "react-bootstrap";
import Footer from './common/footer';
import axios from 'axios';
import { Redirect } from 'react-router-dom';

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [redirectFlag, setRedirectFlag] = useState(false);

    function validateForm() {
        let flag = true;

        flag = email && email.match("[a-zA-z].{1,12}@[A-z0-9]{2,7}[.][A-z]{2,3}") ? flag : false;

        flag = password && password.length > 5 ? flag : false;

        return flag;
    }

    async function handleSubmit() {
        let user = {
            id: -1,
            name: '',
            surname: '',
            email: email,
            password: password,
            phone: '',
            patientsId: -1,
            medicsId: -1,
            rolesId: -1,
            location: '',
            birthdate: '',
            gender: '',

        };
        console.log('fetchiing', user);
        axios({
            'method': 'POST',
            'url': "http://localhost:8080/online-diagnost/test/login",
            'headers': {
                'Content-Type': 'application/json',
            },
            data: user,

        }).then(response => {
            console.log('resp', response);
            if (response.status === 200) {
            
                sessionStorage.removeItem('token');
                sessionStorage.setItem('token', response.data.token);
                sessionStorage.setItem('role', response.data.role);
                setRedirectFlag(true);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    return (
        <>
            <div className="Login">
                <form>
                    <FormGroup controlId="email" bsSize="large">
                        <Form.Label>Email</Form.Label>
                        <FormControl
                            type="email"

                            autoFocus
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                            placeholder='Type your email' />
                    </FormGroup>
                    <FormGroup controlId="password" bsSize="large">
                        <Form.Label>Password</Form.Label>
                        <FormControl
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            type="password"
                            placeholder='Type your password'
                        />
                    </FormGroup>
                    <Button block bsSize="large" disabled={!validateForm()} type="button"
                        onClick={() => handleSubmit()}>
                        Login
                     </Button>
                </form>
            </div>
            <Footer />
            {!redirectFlag ? '' : sessionStorage.getItem('role') == 1 ?
                <Redirect to='/admin.html' /> : <Redirect to='/my-cabinet.html' />}
        </>
    );
}
