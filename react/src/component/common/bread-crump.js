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
                break;
            default:
        }
        return ending;
    }
    return (
        <nav aria-label="breadcrumb">
            <ol className="breadcrumb">
                <li className="breadcrumb-item">
                    <a href="index.html">Home</a>
                </li>
                <li className="breadcrumb-item active" aria-current="page">{addBreadCrump()}</li>
            </ol>
        </nav>
    );
}