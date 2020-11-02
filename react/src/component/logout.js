import React from 'react';
import { Redirect } from 'react-router-dom';



export default function Logout(props) {
    function cleanSessionStorage() {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('email');
        sessionStorage.removeItem('role');
        sessionStorage.removeItem('diagnosId');
    }

    return (
        <>
            {
                cleanSessionStorage()
            }
            <Redirect to='/home'/>
        </>
    );
}