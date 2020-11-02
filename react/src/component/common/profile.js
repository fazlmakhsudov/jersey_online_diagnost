import React, { useState, useEffect } from "react";
import { Button, Row, Form, Col } from "react-bootstrap";
import axios from 'axios';

export default function Profile(props) {
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [birthdate, setBirthdate] = useState("");
    const [location, setLocation] = useState("");
    const [gender, setGender] = useState("male");
    const [password, setPassword] = useState("");
    const [confirmpassword, setConfirmpassword] = useState("");
    const [flag, setFlag] = useState(true);
    const [user, setUser] = useState({});
    let setParentFlag = props.setFlag;

    function getUser() {
        axios({
            'method': 'GET',
            'url': "http://localhost:8080/online-diagnost/users/single",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },

        }).then(response => {

            if (response.status === 200) {
                let user = response.data;
                setUser(user);
                setName(user.name);
                setSurname(user.surname);
                setEmail(user.email);
                setPhone(user.phone);
                setBirthdate(getDate(user.birthdate));
                setLocation(user.location);
                setGender(user.gender);
                setPassword(user.password);
                setConfirmpassword(user.password);
                if (props.medicFlag) {
                    props.setMedicsId(user.medicsId);
                }
            }

        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

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
  

    async function handleSubmit() {
        let userdata = {
            id: user.id,
            name: name,
            surname: surname,
            email: email,
            password: password,
            phone: phone,
            patientsId: user.patientsId,
            medicsId: user.medicsId,
            rolesId: user.rolesId,
            location: location,
            birthdate: birthdate,
            gender: gender,

        };

        axios({
            'method': 'PUT',
            'url': "http://localhost:8080/online-diagnost/users/save",
            'headers': {
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },
            data: userdata,

        }).then(response => {
        
            if (response.status === 200) {
                sessionStorage.removeItem('token');
                sessionStorage.removeItem('email');
                sessionStorage.setItem('token', response.data.token);
                sessionStorage.setItem('email', email);
                setParentFlag(true);
                alert("Profile has been changed");
            }
        }).catch(error => {
            alert('It has appeared \n' + error);
            console.log(error);
        });
    }

    function getDate(number) {
        let date = new Date(number);
        let day = date.getDay() < 10 ? '0' + date.getDay() : date.getDay();
        let month = date.getMonth() < 10 ? '0' + date.getMonth() : date.getMonth(); 
        let rowDate = date.getFullYear() + '-' + day + '-' + month;
        return rowDate;
    }

    useEffect(() => {
        if (flag) {
            getUser();
            setFlag(false);
       
        }
    });

    return (

        <Form>
            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Name
                </Form.Label>
                <Col sm="4">
                    <Form.Control type='text' name='name'
                        autoFocus
                        value={name}
                        onChange={e => setName(e.target.value)} placeholder='Type your name' />
                </Col>
            </Form.Group>

            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Surname
                 </Form.Label>
                <Col sm="4">
                    <Form.Control type='text' name='surname'
                        autoFocus
                        value={surname}
                        onChange={e => setSurname(e.target.value)}
                        placeholder='Type your surname' />

                </Col>
            </Form.Group>

            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Birthdate
                </Form.Label>
                <Col sm="4">
                    <Form.Control type='date' name='birthdate'
                        autoFocus
                        value={birthdate}
                        onChange={e => setBirthdate(e.target.value)}
                        placeholder='Type your birthdate' />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Email
                                </Form.Label>
                <Col sm="4">
                    <Form.Control type='email' name='email'
                        autoFocus
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        placeholder='Type your email' />
                </Col>
            </Form.Group>

            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Phone
                                </Form.Label>
                <Col sm="4">
                    <Form.Control type='text' name='phone'
                        autoFocus
                        value={phone}
                        onChange={e => setPhone(e.target.value)}
                        placeholder='Type your phone' />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Gender
                                </Form.Label>
                <Col sm="4">
                    <Form.Control name='gender' as="select"
                        autoFocus
                        value={gender}
                        onChange={e => setGender(e.target.value)}  >
                        <option value='male'>Male</option>
                        <option value='female'>Female</option>
                    </Form.Control>
                </Col>
            </Form.Group>
            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Location
                </Form.Label>
                <Col sm="4">
                    <Form.Control type='text' name='location'
                        autoFocus
                        value={location}
                        onChange={e => setLocation(e.target.value)}
                        placeholder='Type your location' />
                </Col>
            </Form.Group>

            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Password
                                </Form.Label>
                <Col sm="4">
                    <Form.Control type="password" name='password'
                        autoFocus
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        placeholder="Type your password" />
                </Col>
            </Form.Group>
            <Form.Group as={Row} className='justify-content-center'>
                <Form.Label column sm="2">
                    Confirm password
                 </Form.Label>
                <Col sm="4">
                    <Form.Control type="password" name='confirmpassword'
                        autoFocus
                        value={confirmpassword}
                        onChange={e => setConfirmpassword(e.target.value)}
                        placeholder="Type your password" />
                </Col>
            </Form.Group>


            <Form.Group as={Row} className='justify-content-center'>
                <Button variant="secondary" type="reset" className='w-25 mr-2'>
                    Clear
                </Button>
                <Button variant="primary" type="button" onClick={() => handleSubmit()} className='w-25' disabled={!validateForm()}>
                    Edit
                </Button>
            </Form.Group>
        </Form>

    );
}

