import React from 'react';

export default function BreadCrump(props) {
    function addBreadCrump() {
        let ending = '';
        switch (props.menu) {
            case 'about':
                ending += 'About Us';
                break;
            case 'contact':
                ending += 'Contact';
                break;
            case 'services':
                ending += 'Services';
                break;
            case 'gallery':
                ending += 'Gallery';
                break;
            case 'My cabinet':
                ending += 'My cabinet';
            default:
        }
        return ending;
    }
    return (
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">{addBreadCrump()}</li>
            </ol>
        </nav>
    );
}