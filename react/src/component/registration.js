import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import Footer from './common/footer';
import axios from 'axios';
import { Redirect } from 'react-router-dom';


export default function Registration() {
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [birthdate, setBirthdate] = useState("");
    const [location, setLocation] = useState("");
    const [gender, setGender] = useState("male");
    const [password, setPassword] = useState("");
    const [confirmpassword, setConfirmpassword] = useState("");
    const [redirectFlag, setRedirectFlag] = useState(false);

    function validateForm() {
        let flag = true;
        flag = name && name.length > 3 ? flag : false;
        flag = surname && surname.length > 3 ? flag : false;
        flag = location && location.length > 3 ? flag : false;
        flag = email && email.match("[a-zA-z].{1,12}@[A-z0-9]{2,7}[.][A-z]{2,3}") ? flag : false;
        flag = phone && phone.length > 9 ? flag : false;
        flag = gender && gender.length > 3 ? flag : false;
        flag = password && password.length > 5 ? flag : false;
        flag = confirmpassword && confirmpassword === password ? flag : false;

        return flag;
    }
    function clearFields() {
        setName('');
        setSurname('');
        setEmail('');
        setPassword('');
        setConfirmpassword('');
        setLocation('');
        setPhone('');
        setBirthdate('');

    }

    async function handleSubmit() {
        let user = {
            id: -1,
            name: name,
            surname: surname,
            email: email,
            password: password,
            phone: phone,
            patientsId: -1,
            medicsId: -1,
            rolesId: -1,
            location: location,
            birthdate: birthdate,
            gender: gender,

        };
        console.log('fetchiing', user);
        axios({
            'method': 'POST',
            'url': "http://localhost:8080/online-diagnost/users/register",
            'headers': {
                'Content-Type': 'application/json',
            },
            data: user,

        }).then(response => {
            console.log('resp', response);
            if (response.status === 200) {
                clearFields();
                sessionStorage.removeItem('token');
                sessionStorage.removeItem('role');
                sessionStorage.removeItem('email');
                sessionStorage.setItem('token', response.data.token);
                sessionStorage.setItem('role', response.data.role);
                sessionStorage.setItem('diagnosId', response.data.diagnosId);
                sessionStorage.setItem('email', email);
                setRedirectFlag(true);
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    return (
        <>

            <div className="Registration">
                <h2 className='text-center'>Registration form</h2>
                <Form>
                    <Form.Group>
                        <Form.Label  >
                            Name
                        </Form.Label>
                        <Form.Control type='text' name='name'
                            autoFocus
                            value={name}
                            onChange={e => setName(e.target.value)} placeholder='Type your name' />
                    </Form.Group>

                    <Form.Group >
                        <Form.Label  >
                            Surname
                        </Form.Label>

                        <Form.Control type='text' name='surname'
                            autoFocus
                            value={surname}
                            onChange={e => setSurname(e.target.value)}
                            placeholder='Type your surname' />

                    </Form.Group>

                    <Form.Group controlId="birthdate" >
                        <Form.Label  >
                            Birthdate
                         </Form.Label>

                        <Form.Control type='date' name='birthdate'
                            autoFocus
                            value={birthdate}
                            onChange={e => setBirthdate(e.target.value)}
                            placeholder='Type your birthdate' />

                    </Form.Group>
                    <Form.Group >
                        <Form.Label  >
                            Email
                        </Form.Label>
                        <Form.Control type='email' name='email'
                            autoFocus
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                            placeholder='Type your email' />

                    </Form.Group>

                    <Form.Group >
                        <Form.Label  >
                            Phone
                        </Form.Label>

                        <Form.Control type='text' name='phone'
                            autoFocus
                            value={phone}
                            onChange={e => setPhone(e.target.value)}
                            placeholder='Type your phone' />

                    </Form.Group>
                    <Form.Group>
                        <Form.Label  >
                            Gender
                        </Form.Label>

                        <Form.Control name='gender' as="select"
                            autoFocus
                            value={gender}
                            onChange={e => setGender(e.target.value)}  >
                            <option value='male'>Male</option>
                            <option value='female'>Female</option>
                        </Form.Control>

                    </Form.Group>
                    <Form.Group controlId="location" >
                        <Form.Label  >
                            Location
                        </Form.Label>

                        <Form.Control type='text' name='location'
                            autoFocus
                            value={location}
                            onChange={e => setLocation(e.target.value)}
                            placeholder='Type your location' />

                    </Form.Group>

                    <Form.Group  >
                        <Form.Label  >
                            Password
                         </Form.Label>

                        <Form.Control type="password" name='password'
                            autoFocus
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            placeholder="Type your password" />

                    </Form.Group>
                    <Form.Group  >
                        <Form.Label  >
                            Confirm password
                        </Form.Label>

                        <Form.Control type="password" name='confirmpassword'
                            autoFocus
                            value={confirmpassword}
                            onChange={e => setConfirmpassword(e.target.value)}
                            placeholder="Type your password" />

                    </Form.Group>
                    <Form.Group >
                        <Button variant="secondary" type="reset" className='w-50 '>
                            Reset
                        </Button>
                        <Button variant="primary" type="button" onClick={() => handleSubmit()} className='w-50' disabled={!validateForm()}>
                            Register
                        </Button>
                    </Form.Group>
                </Form>

            </div>
            <Footer />
            {redirectFlag ? <Redirect to='/home' /> : ''}
        </>
    );
}

